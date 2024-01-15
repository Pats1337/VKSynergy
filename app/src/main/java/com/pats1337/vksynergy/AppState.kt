package com.pats1337.vksynergy

sealed class AppState {
    var userId: Long? = null

    object SignedIn : AppState()

    object NotSignedIn : AppState()
}
