package com.naughtsmt.lintu.common

import com.naughtsmt.lintu.BuildConfig
import com.naughtsmt.lintu.R

object Constants {
    const val AUTH_ROUTE = "auth_route"
    const val HOME_ROUTE = "home_route"
    const val ROOT_ROUTE = "root_route"
    const val BASE_URL = "https://api.boardgameatlas.com/"
    const val PARAM_GAME_ID = "gameId"
    const val PARAM_SINGLE_LIST_ID = "singleListId"
    const val PARAM_CODE = "code"
    const val DEFAULT_IMAGE = R.drawable.lintu_logo
    const val CLIENT_ID = BuildConfig.CLIENT_ID
    const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET
    const val REDIRECT_URI = "naughtsmt://callback"
    const val URL_TEXT =
        "https://api.boardgameatlas.com/oauth/authorize?state=true&response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI"
}
