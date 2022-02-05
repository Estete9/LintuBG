package com.naughtsmt.lintu.common

import androidx.compose.runtime.mutableStateOf
import com.naughtsmt.lintu.data.data_source.remote.dto_access_token.AccessTokenDto

object AuthData {
    val authToken = mutableStateOf<AccessTokenDto?>(null)
}