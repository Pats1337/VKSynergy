package com.pats1337.vksynergy

sealed class SignState {
    data class SignedIn(
        val userId: Long = -1,
        val iconId: Int = R.drawable.person,
        val welcomeText: String = userId.toString(),
        val buttonTextId: Int = R.string.signout,
    ) : SignState()

    data class NotSignedIn(
        val iconId: Int = R.drawable.all_inclusive,
        val welcomeTextId: Int = R.string.app_name,
        val buttonTextId: Int = R.string.signin,
    ) : SignState()
}
