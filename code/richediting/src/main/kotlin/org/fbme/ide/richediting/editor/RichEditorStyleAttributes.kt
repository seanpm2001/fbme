package org.fbme.ide.richediting.editor

import jetbrains.mps.editor.runtime.style.InheritableStyleAttribute
import jetbrains.mps.editor.runtime.style.SimpleStyleAttribute
import jetbrains.mps.nodeEditor.cells.EditorCell_Collection
import jetbrains.mps.openapi.editor.EditorContext
import jetbrains.mps.openapi.editor.style.StyleAttribute
import org.fbme.ide.richediting.adapters.ecc.cell.ActionBlock
import org.fbme.ide.richediting.adapters.fbnetwork.FBConnectionCursor
import org.fbme.ide.richediting.adapters.fbnetwork.FBConnectionPath
import org.fbme.ide.richediting.viewmodel.NetworkComponentView
import org.fbme.ide.richediting.viewmodel.NetworkConnectionView
import org.fbme.ide.richediting.viewmodel.NetworkPortView
import org.fbme.lib.iec61499.IEC61499Factory
import org.fbme.lib.iec61499.declarations.AlgorithmDeclaration
import org.fbme.lib.iec61499.declarations.EventDeclaration
import org.fbme.lib.iec61499.descriptors.FBPortDescriptor
import org.fbme.lib.iec61499.descriptors.FBTypeDescriptor
import org.fbme.lib.iec61499.ecc.StateAction
import org.fbme.lib.iec61499.ecc.StateDeclaration
import org.fbme.lib.iec61499.fbnetwork.FBNetwork
import org.fbme.lib.iec61499.fbnetwork.FunctionBlockDeclarationBase
import org.fbme.lib.iec61499.instances.NetworkInstance
import org.fbme.scenes.controllers.SceneViewpoint
import org.fbme.scenes.controllers.SelectionModel
import org.fbme.scenes.controllers.components.ComponentsFacility
import org.fbme.scenes.controllers.diagram.ConnectionsFacility
import org.fbme.scenes.controllers.diagram.DiagramFacility
import java.awt.Point

object RichEditorStyleAttributes {
    @JvmField
    val NETWORK: StyleAttribute<FBNetwork> = InheritableStyleAttribute("fb-network")

    @JvmField
    val NETWORK_INSTANCE: StyleAttribute<NetworkInstance> = InheritableStyleAttribute("fb-network-instance")

    @JvmField
    val PORT: StyleAttribute<FBPortDescriptor> = SimpleStyleAttribute("fb-port")

    @JvmField
    val TYPE: StyleAttribute<FBTypeDescriptor> = InheritableStyleAttribute("fb-type")

    @JvmField
    val FB: StyleAttribute<FunctionBlockDeclarationBase> = InheritableStyleAttribute("fb-inst")

    @JvmField
    val SELECTED_FBS: StyleAttribute<SelectionModel<NetworkComponentView>> = InheritableStyleAttribute("selected-fbs")

    @JvmField
    val ALGORITHMS: StyleAttribute<StateAction> = InheritableStyleAttribute("algo")

    @JvmField
    val OUTPUTS: StyleAttribute<StateAction> = InheritableStyleAttribute("outputs")

    @JvmField
    val ALL_ALGORITHMS: StyleAttribute<MutableList<AlgorithmDeclaration>> = InheritableStyleAttribute("all-outputs")

    @JvmField
    val ALL_OUTPUTS: StyleAttribute<List<EventDeclaration>> = InheritableStyleAttribute("all-outputs")

    @JvmField
    val STATE_COLLECTION: StyleAttribute<EditorCell_Collection> = InheritableStyleAttribute("state-collection")

    @JvmField
    val ACTIONS: StyleAttribute<MutableList<ActionBlock>> = InheritableStyleAttribute("action-block")

    @JvmField
    val STATE_DECLARATION: StyleAttribute<StateDeclaration> = InheritableStyleAttribute("state-declaration")

    @JvmField
    val EDITOR_CONTEXT: StyleAttribute<EditorContext> = InheritableStyleAttribute("editor-context")

    @JvmField
    val FACTORY_DECLARATION: StyleAttribute<IEC61499Factory> = InheritableStyleAttribute("factory-declaration")

    @JvmField
    val DIAGRAM_FACILITY: StyleAttribute<DiagramFacility<NetworkComponentView, NetworkPortView, NetworkConnectionView, Point>> =
        InheritableStyleAttribute("diagram-facility")

    @JvmField
    val COMPONENTS_FACILITY: StyleAttribute<ComponentsFacility<NetworkComponentView, Point>> =
        InheritableStyleAttribute("components-facility")

    @JvmField
    val CONNECTIONS_FACILITY: StyleAttribute<ConnectionsFacility<NetworkComponentView, NetworkPortView, NetworkConnectionView, FBConnectionCursor, FBConnectionPath>> =
        InheritableStyleAttribute("connections-facility")

    @JvmField
    val VIEWPOINT: StyleAttribute<SceneViewpoint> = InheritableStyleAttribute("viewpoint")

    init {
        NETWORK.register()
        NETWORK_INSTANCE.register()
        PORT.register()
        TYPE.register()
        FB.register()
        SELECTED_FBS.register()
        ALGORITHMS.register()
        OUTPUTS.register()
        ALL_ALGORITHMS.register()
        ALL_OUTPUTS.register()
        STATE_COLLECTION.register()
        ACTIONS.register()
        STATE_DECLARATION.register()
        EDITOR_CONTEXT.register()
        FACTORY_DECLARATION.register()
        DIAGRAM_FACILITY.register()
        COMPONENTS_FACILITY.register()
        CONNECTIONS_FACILITY.register()
        VIEWPOINT.register()
    }
}