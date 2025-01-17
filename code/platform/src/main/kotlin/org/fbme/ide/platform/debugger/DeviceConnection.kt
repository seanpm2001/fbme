package org.fbme.ide.platform.debugger

import org.jetbrains.mps.openapi.model.SNode
import java.io.Closeable
import java.io.IOException

interface DeviceConnection : Closeable {
    val isAlive: Boolean

    @Throws(IOException::class)
    fun deployResource(resource: SNode)

    @Throws(IOException::class)
    fun addWatch(watchable: Watchable)

    @Throws(IOException::class)
    fun removeWatch(watchable: Watchable)

    @Throws(IOException::class)
    fun readWatches(): String
}
