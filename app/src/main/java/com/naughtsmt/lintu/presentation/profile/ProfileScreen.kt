package com.naughtsmt.lintu.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun ProfileScreen(
//    mainViewModel: MainViewModel
//viewModel: ListsViewModel = hiltViewModel()
) {
//val state = viewModel.state.value
//    mainViewModel.setCurrentScreen(Screens.NavBarScreens.ProfileScreen)

    Box(contentAlignment = Alignment.Center) {
        Text(text = "Profile Screen")
    }
}