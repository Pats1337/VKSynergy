package com.pats1337.vksynergy

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignInScreen(state: SignState.NotSignedIn, onSignInClick: () -> Unit) {
    SignContent(
        iconId = state.iconId,
        welcomeText = stringResource(id = state.welcomeTextId),
        buttonTextId = state.buttonTextId,
        onButtonClick = onSignInClick
    )
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(state = SignState.NotSignedIn(), onSignInClick = {})
}