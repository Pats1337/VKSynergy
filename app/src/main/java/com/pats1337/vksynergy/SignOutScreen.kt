package com.pats1337.vksynergy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignOutScreen(state: SignState, onSignOutClick: () -> Unit) {
    SignContent(
        iconId = R.drawable.person,
        welcomeText = state.userId.toString(),
        buttonTextId = R.string.signout,
        onButtonClick = onSignOutClick
    )
}

@Preview
@Composable
fun SignOutScreenPreview() {
    SignOutScreen(state = SignState.SignedIn, onSignOutClick = { })
}