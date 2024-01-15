package com.pats1337.vksynergy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme

@Composable
fun SignScreen(
    state: AppState, onSignInClick: () -> Unit, onSignOutClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            when (state){
                is AppState.SignedIn -> SignOutScreen(state, onSignOutClick)
                is AppState.NotSignedIn -> SignInScreen(onSignInClick)
            }
        }
    }
}

@Composable
@Preview
fun AuthScreenPreview() {
    VKSynergyTheme {

    }
}