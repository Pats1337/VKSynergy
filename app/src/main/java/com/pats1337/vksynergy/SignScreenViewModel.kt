package com.pats1337.vksynergy

import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignScreenViewModel @Inject constructor() : ViewModel() {
    private val _signState = MutableStateFlow<SignState>(SignState.NotSignedIn())
    val signState: StateFlow<SignState> get() = _signState.asStateFlow()

    fun onSignClick(activity: AppActivity) {
        when (signState.value) {
            is SignState.SignedIn -> handleSignOut()
            is SignState.NotSignedIn -> handleSignIn(activity)
        }
    }

    fun handleSignInSuccessful(token: VKAccessToken?) {
        if (token != null) {
            _signState.value = SignState.SignedIn(welcomeText = token.userId.toString())
        }
    }

    fun handleSignInFailed(authException: VKAuthException) {
        _signState.value = SignState.NotSignedIn()
    }

    private fun handleSignIn(activity: AppActivity) {
        VK.login(activity, VKScope.values().dropLast(1))
    }

    private fun handleSignOut() {
        VK.logout()
        _signState.value = SignState.NotSignedIn()
    }
}