package com.pats1337.vksynergy

import androidx.compose.runtime.Composable

@Composable
fun SignOutScreen(state: AppState, onSignOutClick: () -> Unit) {
    SignContent(
        iconId = R.drawable.person,
        welcomeText = state.userId.toString(),
        buttonTextId = R.string.signout,
        onButtonClick = onSignOutClick
    )
}