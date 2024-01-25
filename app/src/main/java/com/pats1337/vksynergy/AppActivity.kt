package com.pats1337.vksynergy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.pats1337.vksynergy.ui.theme.VKSynergyTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKAuthException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    @Inject
    lateinit var vkLoginController: VkLoginController

    //    private val viewModel: SignScreenViewModel by viewModels()
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKSynergyTheme {
                SignScreen()
                Log.d(
                    "debug",
                    "Вызываем логин скрин из ОнКреэйт Активити" + vkLoginController.vkLoginControllerState.value.toString()
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        vkLoginController.onAuthActivityResult(requestCode, resultCode, data)
        Log.d(
            "debug",
            "Стейт контроллера после вызова ОнАктивитиРезалт" + vkLoginController.vkLoginControllerState.value.toString()
        )
//        super.onActivityResult(requestCode, resultCode, data)
//        val callback = object : VKAuthCallback {
//            override fun onLogin(token: VKAccessToken) {
////                viewModel.handleSignInSuccessful(token)
//            }
//
//            override fun onLoginFailed(authException: VKAuthException) {
////                viewModel.handleSignInFailed(authException)
//            }
//        }
        if (data == null || !VK.onActivityResult(
                requestCode,
                resultCode,
                data,
                vkLoginController
            )
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}