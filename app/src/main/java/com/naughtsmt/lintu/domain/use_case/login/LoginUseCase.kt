package com.naughtsmt.lintu.domain.use_case.login

import android.content.Context
import androidx.navigation.NavController
import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.common.Constants.REDIRECT_URI
import com.naughtsmt.lintu.presentation.Screen

class LoginUseCase(
//    private val context: Context,
    val navController: NavController
) {

//    private val urlText =
//        "https://api.boardgameatlas.com/oauth/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI"

    operator fun invoke() {
//        navController.navigate(Screen.WebViewScreen.route +"/$urlText")

//        val intent = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse(urlText)
//        )
//        context.startActivity(intent)
    }


}



