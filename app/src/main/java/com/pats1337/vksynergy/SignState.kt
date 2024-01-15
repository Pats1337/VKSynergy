package com.pats1337.vksynergy

sealed class SignState {
    data class SignedIn (val userId: Long = -1) : SignState()
    object NotSignedIn : SignState()
}
