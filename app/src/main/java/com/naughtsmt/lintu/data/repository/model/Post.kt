package com.naughtsmt.lintu.data.repository.model

import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.common.Constants.CLIENT_SECRET
import com.naughtsmt.lintu.common.Constants.REDIRECT_URI

data class Post(
    val client_id: String = CLIENT_ID,
    val client_secret: String = CLIENT_SECRET,
    val code: String,
    val redirect_uri: String = REDIRECT_URI,
    val grant_type: String = "authorization_code"
)