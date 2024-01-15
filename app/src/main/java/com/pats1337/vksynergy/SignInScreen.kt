package com.pats1337.vksynergy

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignInScreen(onSignInClick: () -> Unit) {
    SignContent(
        iconId = R.drawable.all_inclusive,
        welcomeText = stringResource(id = R.string.app_name),
        buttonTextId = R.string.signin,
        onButtonClick = onSignInClick
    )
}

@Preview
@Composable
fun SignInScreenPreview(){
    SignInScreen(onSignInClick = {})
}