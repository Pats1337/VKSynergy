package com.pats1337.vksynergy

import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.exceptions.VKAuthException

sealed interface VkLoginControllerState {
    data class SignedIn(val token: VKAccessToken) : VkLoginControllerState
    object SignedOut : VkLoginControllerState
    data class Error(val authException: VKAuthException) : VkLoginControllerState
    object None : VkLoginControllerState

}
