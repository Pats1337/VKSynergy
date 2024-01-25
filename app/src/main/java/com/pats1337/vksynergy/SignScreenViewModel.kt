package com.pats1337.vksynergy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignScreenViewModel @Inject constructor(
    private val vkLoginController: VkLoginController
) : ViewModel() {
    private val _signState = MutableStateFlow<SignState>(SignState.NotSignedIn())
    val signState: StateFlow<SignState> get() = _signState.asStateFlow()

    init {
        observeVkLoginControllerState()
    }

    fun onSignClick(activity: AppActivity) {
        when (signState.value) {
            is SignState.SignedIn -> handleSignOut()
            is SignState.NotSignedIn -> handleSignIn(activity)
        }
    }

//    fun handleSignInSuccessful(token: VKAccessToken?) {
//        if (token != null) {
//            _signState.value = SignState.SignedIn(welcomeText = token.userId.toString())
//        }
//    }
//
//    fun handleSignInFailed(authException: VKAuthException) {
//        _signState.value = SignState.NotSignedIn()
//    }

    private fun handleSignIn(activity: AppActivity) {
        vkLoginController.signIn(activity)
//        VK.login(activity, VKScope.values().dropLast(1))
    }

    private fun handleSignOut() {
        vkLoginController.signOut()
//        VK.logout()
//        _signState.value = SignState.NotSignedIn()
    }

    private fun observeVkLoginControllerState() {
        viewModelScope.launch {
            vkLoginController.vkLoginControllerState.collect { vkLoginControllerState ->
                handleVkLoginControllerState(vkLoginControllerState)
                Log.d("debug", "Стейт контроллера в момент Коллекта $vkLoginControllerState")
            }
        }
    }

    private fun handleVkLoginControllerState(vkLoginControllerState: VkLoginControllerState) {
        Log.d("debug","Стейт контроллера перед изменением стейта экрана $vkLoginControllerState $signState")
        when (vkLoginControllerState) {
            is VkLoginControllerState.SignedIn -> {
                _signState.value =
                    SignState.SignedIn(welcomeText = vkLoginControllerState.token.userId.toString())
            }

            VkLoginControllerState.SignedOut -> _signState.value = SignState.NotSignedIn()

            is VkLoginControllerState.Error -> {
                _signState.value = SignState.NotSignedIn()
            }

            VkLoginControllerState.None -> {}
        }
    }
}