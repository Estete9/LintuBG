package com.naughtsmt.lintu.common

import android.content.Context
import com.naughtsmt.lintu.R

object Constants {
    const val BASE_URL = "https://api.boardgameatlas.com/"
    const val PARAM_GAME_ID = "gameId"
    const val PARAM_CODE = "code"
    const val PARAM_GAME_IMAGE_URL = "gameImageUrl"
    const val DEFAULT_IMAGE = R.drawable.lintu_logo
    const val CLIENT_ID = "HuPj3g2QXa"
    const val CLIENT_SECRET = "640fd2bb1534aacf23d10a3deb74a697"
    const val REDIRECT_URI = "naughtsmt://callback"
    const val URL_TEXT =
        "https://api.boardgameatlas.com/oauth/authorize?state=true&response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI"
}