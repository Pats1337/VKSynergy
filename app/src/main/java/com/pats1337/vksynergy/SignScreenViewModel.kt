package com.pats1337.vksynergy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private fun handleSignIn(activity: AppActivity) {
        vkLoginController.signIn(activity)
    }

    private fun handleSignOut() {
        vkLoginController.signOut()
    }

    private fun observeVkLoginControllerState() {
        viewModelScope.launch {
            vkLoginController.vkLoginControllerState.collect { vkLoginControllerState ->
                handleVkLoginControllerState(vkLoginControllerState)
            }
        }
    }

    private fun handleVkLoginControllerState(vkLoginControllerState: VkLoginControllerState) {
        when (vkLoginControllerState) {
            is VkLoginControllerState.SignedIn -> {
                _signState.value =
                    SignState.SignedIn(welcomeText = vkLoginControllerState.token.userId.toString())
            }
            is VkLoginControllerState.Error -> {
                _signState.value = SignState.NotSignedIn()
            }
            VkLoginControllerState.SignedOut -> _signState.value = SignState.NotSignedIn()
            VkLoginControllerState.None -> {}
        }
    }
}