package com.pats1337.vksynergy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignOutScreen(state: SignState.SignedIn, onSignOutClick: () -> Unit) {
    SignContent(
        iconId = state.iconId,
        welcomeText = state.userId.toString(),
        buttonTextId = state.buttonTextId,
        onButtonClick = onSignOutClick
    )
}

@Preview
@Composable
fun SignOutScreenPreview() {
    SignOutScreen(state = SignState.SignedIn()) {}
}