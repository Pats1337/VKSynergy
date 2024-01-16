package com.pats1337.vksynergy


sealed class SignState {
    open val iconId: Int = -1
    open val welcomeText: String = "welcomeText"
    open val buttonTextId: Int = -3

    data class SignedIn(
        override val iconId: Int = R.drawable.person,
        override val welcomeText: String,
        override val buttonTextId: Int = R.string.signout,
    ) : SignState()

    data class NotSignedIn(
        override val iconId: Int = R.drawable.all_inclusive,
        override val buttonTextId: Int = R.string.signin,
    ) : SignState()
}
