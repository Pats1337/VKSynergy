package com.pats1337.vksynergy

import android.content.Intent
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKAuthException
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ActivityRetainedScoped
class VkLoginController @Inject constructor() : VKAuthCallback {

    private val _vkLoginControllerState =
        MutableStateFlow<VkLoginControllerState>(VkLoginControllerState.None)
    val vkLoginControllerState: StateFlow<VkLoginControllerState> =
        _vkLoginControllerState.asStateFlow()

    fun onAuthActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, this)) {
        }
    }

    override fun onLogin(token: VKAccessToken) {
        _vkLoginControllerState.value = VkLoginControllerState.SignedIn(token = token)
    }

    override fun onLoginFailed(authException: VKAuthException) {
        _vkLoginControllerState.value = VkLoginControllerState.Error(authException = authException)
    }


    fun signIn(activity: AppActivity) {
        VK.login(activity, VKScope.values().dropLast(1))
    }

    fun signOut() {
        VK.logout()
        _vkLoginControllerState.value = VkLoginControllerState.SignedOut
    }

}