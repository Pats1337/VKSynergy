package com.pats1337.vksynergy

sealed class SignState {
    var userId: Long? = null

    object SignedIn : SignState()

    object NotSignedIn : SignState()
}
