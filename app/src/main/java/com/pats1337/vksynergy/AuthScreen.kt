package com.pats1337.vksynergy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme

@Composable
fun AuthScreen(
    signConfig: SignConfig
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            SignHost(signConfig)
        }
    }
}

@Composable
@Preview
fun AuthScreenPreview() {
    VKSynergyTheme {
        AuthScreen(
            signConfig = SignConfig(
                welcomeText = stringResource(id = R.string.app_name),
                buttonTextId = R.string.logout,
                iconResourceId = R.drawable.person,
                onButtonClick = {})
        )
    }
}

data class SignConfig(
    val welcomeText: String,
    val buttonTextId: Int,
    val iconResourceId: Int,
    val onButtonClick: () -> Unit
)