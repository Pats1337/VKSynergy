package com.pats1337.vksynergy

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun SignInScreen(onSignInClick: () -> Unit) {
    SignContent(
        iconId = R.drawable.all_inclusive,
        welcomeText = stringResource(id = R.string.app_name),
        buttonTextId = R.string.signin,
        onButtonClick = onSignInClick
    )
}