package com.pats1337.vksynergy


sealed interface SignState {
    val iconId: Int
    val welcomeText: Any
    val buttonTextId: Int

    data class SignedIn(
        override val iconId: Int = R.drawable.person,
        override val welcomeText: String,
        override val buttonTextId: Int = R.string.signout,
    ) : SignState

    data class NotSignedIn(
        override val iconId: Int = R.drawable.all_inclusive,
        override val welcomeText: Int = R.string.app_name,
        override val buttonTextId: Int = R.string.signin,
    ) : SignState
}
