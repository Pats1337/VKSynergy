package com.pats1337.vksynergy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme

@Composable
fun SignScreen() {
    val viewModel: SignScreenViewModel = viewModel()
    val activity: AppActivity = LocalContext.current as AppActivity
    val state by viewModel.signState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            SignContent(state = state) { viewModel.onSignClick(activity) }
        }
    }
}

@Composable
@Preview
fun SignScreenPreview() {
    VKSynergyTheme {
        SignScreen()
    }
}