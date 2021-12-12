package com.naughtsmt.lintu.presentation

//import com.naughtsmt.lintu.presentation.image_screen.ImageScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.LifecycleOwner
import com.naughtsmt.lintu.navigation.NavigationHost
import com.naughtsmt.lintu.presentation.ui.theme.LintuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            LintuTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    NavigationHost(/*context = this*//*, lifeCycleOwner = lifecycleOwner*/)
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//
////        val intent = Intent()
//        val data: Uri? = intent.data
////            val action: String? = intent.action
//        if (data != null && data.toString().startsWith(Constants.URL_TEXT)) {
//            codeObject.authToken = data.getQueryParameter("code")
////            val code = data.getQueryParameter("code")
//            Toast.makeText(context, "code: $code", Toast.LENGTH_SHORT).show()
//            viewModel.getAuthToken(code, context)
//        }
//
//    }
}

