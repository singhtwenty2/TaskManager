package com.aryan.taskmanager.presentation.auth_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aryan.taskmanager.data.api.util.AuthResult

@Composable
fun AuthScreenComposable(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect() { result ->
            when (result) {
                is AuthResult.Authorized -> {

                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "Authorization Failed.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "Something went wrong.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = state.signUpName,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpNameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Name")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.signUpEmail,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpEmailChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.signUpPassword,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            }
        )
    }
}