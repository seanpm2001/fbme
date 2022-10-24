package org.fbme.ide.platform.debugger

import org.fbme.lib.iec61499.declarations.ResourceDeclaration
import org.fbme.lib.iec61499.fbnetwork.FunctionBlockDeclaration
import java.util.*

data class WatchablePath(val root: ResourceDeclaration, val path: List<FunctionBlockDeclaration>) {

    fun serialize(): WatchablePathData {
        return WatchablePathData(root.identifier, path.map { it.identifier })
    }

    override fun toString(): String {
        return root.name + "." + path.joinToString(".") { it.name }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is WatchablePath) {
            return false
        }
        return this.root == other.root && path.contentEquals(other.path)
    }

    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + root.hashCode()
        result = 31 * result + Arrays.hashCode(path)
        return result
    }
}