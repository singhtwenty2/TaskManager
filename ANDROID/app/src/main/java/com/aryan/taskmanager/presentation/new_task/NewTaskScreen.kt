package com.aryan.taskmanager.presentation.new_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryan.taskmanager.ui.theme.spotifyBG

@Composable
fun NewTaskComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    var title by remember {
        mutableStateOf("")
    }
    var desc by remember {
        mutableStateOf("")
    }
    var dueD by remember {
        mutableStateOf("")
    }
    var priority by remember {
        mutableStateOf("")
    }
    var isDone by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(text = "Title")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.
            fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = desc,
            onValueChange = {
                desc = it
            },
            label = {
                Text(text = "Description")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.
            fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = desc,
            onValueChange = {
                desc = it
            },
            label = {
                Text(text = "Description")
            }
        )
    }
}