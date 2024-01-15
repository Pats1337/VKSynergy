package com.pats1337.vksynergy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme

@Composable
fun SignScreen(
    viewModel: SignScreenViewModel, onSignInClick: () -> Unit, onSignOutClick: () -> Unit
) {
    val state by viewModel.signState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            when (state) {
                is SignState.SignedIn -> SignOutScreen(state as SignState.SignedIn, onSignOutClick)
                is SignState.NotSignedIn -> SignInScreen(onSignInClick)
            }
        }
    }
}

@Composable
@Preview
fun SignScreenPreview() {
    VKSynergyTheme {
        SignScreen(
            viewModel = SignScreenViewModel(),
            onSignInClick = { },
            onSignOutClick = { }
        )
    }
}