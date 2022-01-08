package com.naughtsmt.lintu.presentation.web_view

import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.naughtsmt.lintu.common.Constants.URL_TEXT
import com.naughtsmt.lintu.presentation.Screens

@Composable
fun WebViewScreen(
    navController: NavController,
) {
    Column(Modifier.fillMaxSize()) {
        val isLoading = remember {
            mutableStateOf(true)
        }
        if (isLoading.value) {
            CustomWebView(navController = navController, isLoading = isLoading)
        }
    }
}


@Composable
fun CustomWebView(navController: NavController, isLoading: MutableState<Boolean>) {
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
                        isLoading.value = false
                        if (url != null && url.startsWith("naught")) {
                            val uri = Uri.parse(url)
                            val code =
                                uri.getQueryParameter("code").toString()
                            navController.navigate(Screens.AuthScreen.route + "/$code") {
                                navController.popBackStack()
                            }
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