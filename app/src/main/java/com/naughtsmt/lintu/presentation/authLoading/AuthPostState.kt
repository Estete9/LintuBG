package com.naughtsmt.lintu.presentation.authLoading

import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto

data class AuthPostState(
    val isLoading: Boolean = false,
    val accessToken: AccessTokenDto? = null,
    val error: String = "",
    val code: String = ""
)
