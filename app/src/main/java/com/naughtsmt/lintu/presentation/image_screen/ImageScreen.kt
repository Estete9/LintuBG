package com.naughtsmt.lintu.presentation.image_screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.naughtsmt.lintu.R
//import com.naughtsmt.lintu.presentation.game_detail.GameDetailViewModel
//import com.naughtsmt.lintu.presentation.loadPicture
//TODO find a way to pass the info of the images' url
//@Composable
//fun ImageScreen() {
//
//    val game = viewModel.state.value.game
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        game?.image_url.let { url ->
//            val image = url?.let {
//                loadPicture(
//                    url = it,
//                    defaultImage = R.drawable.lintu_logo
//                ).value
//            }
//            image?.let { img ->
//                Image(
//                    bitmap = img.asImageBitmap(),
//                    contentScale = ContentScale.FillHeight,
//                    contentDescription = "${game?.name} image"
//                )
//            }
//
//        }
//    }
//}