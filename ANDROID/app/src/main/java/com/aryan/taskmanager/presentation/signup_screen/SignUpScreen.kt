package com.aryan.taskmanager.presentation.signup_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SignUpComposable(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier.padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = name, onValueChange = {
                    name = it
                },
                    label = {
                        Text(text = "Name")
                    })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = email, onValueChange = {
                    email = it
                },
                    label = {
                        Text(text = "Email")
                    })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = pass, onValueChange = {
                    pass = it
                },
                    label = {
                        Text(text = "Password")
                    })
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "CREATE ACCOUNT")
                }
            }
        }
    }
}