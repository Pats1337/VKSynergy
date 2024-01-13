package com.pats1337.vksynergy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.pats1337.vksynergy.App.Companion.prefs
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKAuthException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    var authState: AppState by mutableStateOf(
        AppState.NotLoggedIn
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authState = if (VK.isLoggedIn()) {
            AppState.LoggedIn
        } else {
            AppState.NotLoggedIn
        }
        setContent {
            VKSynergyTheme {
                when (authState) {
                    AppState.LoggedIn -> AuthScreen(
                        SignConfig(
                            welcomeText = prefs!!.userId.toString(),
                            buttonTextId = R.string.logout,
                            iconResourceId = R.drawable.person,
                            onButtonClick = {
                                handleLogout()
                            }
                        )
                    )

                    AppState.NotLoggedIn ->
                        AuthScreen(
                            SignConfig(
                                welcomeText = stringResource(id = R.string.app_name),
                                buttonTextId = R.string.login,
                                iconResourceId = R.drawable.all_inclusive,
                                onButtonClick = {
                                    handleLogin()
                                }
                            )
                        )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                authState = AppState.LoggedIn
                prefs!!.userId = token.userId.value
                Log.d("token", token.userId.toString())
            }

            override fun onLoginFailed(authException: VKAuthException) {
                authState = AppState.NotLoggedIn
                prefs!!.userId = -1
                Log.d(this::class.java.simpleName, authException.toString())
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleLogin() {
        VK.login(this@AppActivity, VKScope.values().dropLast(1))
    }

    private fun handleLogout() {
        VK.logout()
        prefs!!.userId = -1
        authState = AppState.NotLoggedIn
    }

}