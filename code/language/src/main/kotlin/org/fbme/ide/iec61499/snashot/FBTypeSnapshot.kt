package org.fbme.ide.iec61499.snashot

import jetbrains.mps.smodel.CopyUtil
import jetbrains.mps.smodel.SModelId
import jetbrains.mps.smodel.SModelReference
import jetbrains.mps.smodel.TrivialModelDescriptor
import jetbrains.mps.smodel.tempmodel.TempModule
import jetbrains.mps.util.ReferenceUpdater
import org.fbme.ide.iec61499.repository.PlatformElement
import org.fbme.ide.iec61499.repository.PlatformElementsOwner
import org.fbme.lib.common.Declaration
import org.fbme.lib.common.Element
import org.fbme.lib.iec61499.declarations.CompositeFBTypeDeclaration
import org.fbme.lib.iec61499.declarations.FBTypeDeclaration
import org.jetbrains.mps.openapi.model.SModel
import org.jetbrains.mps.openapi.model.SNode

class FBTypeSnapshot(
    val snapshotDeclaration: FBTypeDeclaration,
    private val originalOwner: PlatformElementsOwner,
    private val temporaryModels: Map<SModel, SModel>
) {

    /**
     * Find original element of given snapshot element.
     *
     * Requires read access.
     */
    fun <T : Element> findOriginal(element: T): T? {
        if (element !is PlatformElement) return null

        val node = element.node
        val model = node.model ?: return null

        val originalModel = temporaryModels[model] ?: return null
        val originalNode = originalModel.getNode(node.nodeId) ?: return null

        return originalOwner.getAdapter(originalNode, element.javaClass)
    }

    companion object {

        /**
         * Creates a deep snapshot of given declaration.
         *
         * Requires read access.
         */
        @JvmStatic
        fun create(
            typeDeclaration: FBTypeDeclaration
        ): FBTypeSnapshot {
            check(typeDeclaration is PlatformElement)

            val snapshotModule = TempModule(emptySet(), false, false)

            val originalToTemporaryModels = hashMapOf<SModel, TrivialModelDescriptor>()

            val declarations = hashSetOf<Declaration>()
            collectAllDeclarations(typeDeclaration, declarations)
            lateinit var resultDeclaration: FBTypeDeclaration

            for (declaration in declarations) {
                check(declaration is PlatformElement)
                val originalModel = checkNotNull(declaration.node.model)
                val snapshotModel = originalToTemporaryModels.getOrPut(originalModel) {
                    SnapshotModel(originalModel).also { snapshotModule.registerModel(it) }
                }
                val snapshotNode = CopyUtil.copyAndPreserveId(declaration.node)
                snapshotModel.addRootNode(snapshotNode)
                if (declaration == typeDeclaration) {
                    resultDeclaration =
                        PlatformElementsOwner().getAdapter(snapshotNode, FBTypeDeclaration::class.java)!!
                }
            }

            val referenceUpdater = ReferenceUpdater()
            for ((original, temporary) in originalToTemporaryModels.entries) {
                referenceUpdater.addModelToAdjust(original, temporary)
            }
            referenceUpdater.adjust()

            return FBTypeSnapshot(
                resultDeclaration,
                typeDeclaration.owner,
                originalToTemporaryModels.entries.associate { it.value to it.key }
            )
        }

        private fun collectAllDeclarations(declaration: Declaration, result: MutableSet<Declaration>) {
            if (!result.add(declaration)) {
                return
            }
            if (declaration is CompositeFBTypeDeclaration) {
                for (it in declaration.network.functionBlocks) {
                    collectAllDeclarations(it.type.declaration ?: continue, result)
                }
            }
        }
    }

    class SnapshotModel(
        val originalModel: SModel,
        reference: SModelReference = SModelReference(null, SModelId.generate(), originalModel.name)
    ) : TrivialModelDescriptor(jetbrains.mps.smodel.SModel(reference)) {

        override fun addRootNode(node: SNode) {
            sModelInternal.addRootNode(node)
        }

        override fun isReadOnly(): Boolean {
            return false
        }
    }
}
