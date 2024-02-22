package com.pats1337.vksynergy

import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.Gson
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
class VkLoginController @Inject constructor(private val sharedPreferences: SharedPreferences) :
    VKAuthCallback {

    companion object {
        private const val VK_ACCESS_TOKEN_KEY = "vk_access_token"
    }

    private val _state =
        MutableStateFlow<VkLoginControllerState>(VkLoginControllerState.None)
    val state: StateFlow<VkLoginControllerState> =
        _state.asStateFlow()

    init {
        val savedToken = getSavedToken()
        if (savedToken != null) {
            _state.value = VkLoginControllerState.SignedIn(savedToken)
        } else {
            _state.value = VkLoginControllerState.None
        }
    }

    fun onAuthActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VK.onActivityResult(requestCode, resultCode, data, this)
    }

    override fun onLogin(token: VKAccessToken) {
        saveToken(token)
        _state.value = VkLoginControllerState.SignedIn(token = token)
    }

    override fun onLoginFailed(authException: VKAuthException) {
        _state.value = VkLoginControllerState.Error(authException = authException)
    }

    fun signIn(activity: AppActivity) {
        VK.login(activity, VKScope.values().dropLast(1))
    }

    fun signOut() {
        sharedPreferences.edit().remove(VK_ACCESS_TOKEN_KEY).apply()
        VK.logout()
        _state.value = VkLoginControllerState.SignedOut
    }

    private fun saveToken(token: VKAccessToken) {
        val gson = Gson()
        val tokenJson = gson.toJson(token)
        sharedPreferences.edit().putString(VK_ACCESS_TOKEN_KEY, tokenJson).apply()
    }

    private fun getSavedToken(): VKAccessToken? {
        val gson = Gson()
        val tokenJson = sharedPreferences.getString(VK_ACCESS_TOKEN_KEY, null)
        return if (tokenJson != null) {
            gson.fromJson(tokenJson, VKAccessToken::class.java)
        } else {
            null
        }
    }
}