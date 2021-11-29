package com.naughtsmt.lintu.common

import com.naughtsmt.lintu.R

object Constants {
    const val BASE_URL = "https://api.boardgameatlas.com/api/"
    const val PARAM_GAME_ID = "gameId"
    const val PARAM_GAME_IMAGE_URL = "gameImageUrl"
    const val DEFAULT_IMAGE = R.drawable.lintu_logo
    const val CLIENT_ID = "HuPj3g2QXa"
    const val REDIRECT_URI = "naughtsmt://callback"
    const val URL_TEXT=
        "https://api.boardgameatlas.com/oauth/authorize?response_type=code&client_id=${Constants.CLIENT_ID}&redirect_uri=${Constants.REDIRECT_URI}"

}