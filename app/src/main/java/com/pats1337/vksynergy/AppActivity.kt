package com.pats1337.vksynergy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKAuthException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    var authState: SignState by mutableStateOf(
        SignState.NotSignedIn
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authState = if (VK.isLoggedIn()) {
            SignState.SignedIn
        } else {
            SignState.NotSignedIn
        }
        setContent {
            VKSynergyTheme {
                SignScreen(
                    state = authState,
                    onSignInClick = { handleSignIn() },
                    onSignOutClick = { handleSignOut() }
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                authState = SignState.SignedIn
                authState.userId = token.userId.value
                Log.d("token", token.userId.toString())
            }

            override fun onLoginFailed(authException: VKAuthException) {
                authState = SignState.NotSignedIn
                authState.userId = -1
                Log.d(this::class.java.simpleName, authException.toString())
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleSignIn() {
        VK.login(this@AppActivity, VKScope.values().dropLast(1))
    }

    private fun handleSignOut() {
        VK.logout()
        authState.userId = -1
        authState = SignState.NotSignedIn
    }

}