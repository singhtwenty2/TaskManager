package com.aryan.taskmanager.presentation.home_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposable(
//    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    Scaffold { value ->

    }
}