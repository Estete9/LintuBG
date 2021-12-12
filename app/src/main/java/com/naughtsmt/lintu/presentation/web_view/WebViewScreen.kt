package com.naughtsmt.lintu.presentation.web_view

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.naughtsmt.lintu.common.Constants.URL_TEXT
import com.naughtsmt.lintu.presentation.Screen

var code = ""

@Composable
fun WebViewScreen(
    navController: NavController,
//    authTokenViewModel: AuthPostViewModel,

) {

val tag: String = "WEB_VIEW_SCREEN"

    Column(Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            url: String?
                        ): Boolean {
                            if (url != null && url.contains("code")) {
                                val uri = Uri.parse(url)
                                code =
                                    uri.getQueryParameter("code").toString()
                                Log.d(tag, "the code is: $code")
                                navController.navigate(Screen.ListsScreen.route + "&$code")
                                return false
                            }

                            return true
                        }
                    }
                    loadUrl(URL_TEXT)
                }
            },
        )
    }
}
