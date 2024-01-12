package com.pats1337.vksynergy

sealed class AppState {
    object LoggedIn : AppState()
    object NotLoggedIn : AppState()
}
