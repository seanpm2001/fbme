package org.fbme.lib.iec61499.declarations

import org.fbme.lib.iec61499.fbnetwork.FBNetwork

interface CompositeFBTypeDeclaration : FBTypeDeclaration {
    val network: FBNetwork
}
