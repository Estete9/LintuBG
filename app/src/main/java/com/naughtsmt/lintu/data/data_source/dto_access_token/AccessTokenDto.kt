package com.naughtsmt.lintu.data.data_source.dto_access_token

data class AccessTokenDto(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String
)