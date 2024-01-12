package com.pats1337.vksynergy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    val authState = mutableStateOf(
        if (VK.isLoggedIn()) {
            AppState.LoggedIn
        } else {
            AppState.NotLoggedIn
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when (authState.value) {
                AppState.LoggedIn -> LogOutScreen()
                AppState.NotLoggedIn -> LogInScreen()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                authState.value = AppState.LoggedIn
                prefs!!.userId = token.userId.value
                Log.d("token", token.userId.toString())
            }

            override fun onLoginFailed(authException: VKAuthException) {
                authState.value = AppState.NotLoggedIn
                prefs!!.userId = -1
                Log.d(this::class.java.simpleName, authException.toString())
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @Composable
    fun LogInScreen() {
        VKSynergyTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.all_inclusive),
                        contentDescription = "icon",
                        modifier = Modifier
                            .align((Alignment.CenterHorizontally))
                            .padding(top = 100.dp)
                            .size(80.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .height(72.dp)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        onClick = {
                            VK.login(this@AppActivity, VKScope.values().dropLast(1))
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun LogOutScreen() {
        VKSynergyTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "icon",
                        modifier = Modifier
                            .align((Alignment.CenterHorizontally))
                            .padding(top = 100.dp)
                            .size(80.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = prefs?.userId.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .height(72.dp)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        onClick = {
                            VK.logout()
                            authState.value = AppState.NotLoggedIn
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun LogInPreview() {
        LogInScreen()
    }

}