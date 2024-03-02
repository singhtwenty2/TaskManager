package com.aryan.taskmanager.presentation.signup_screen

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.getSystemService
import androidx.navigation.NavHostController
import com.aryan.taskmanager.R
import com.aryan.taskmanager.presentation.navigation.NavigationRoute
import com.aryan.taskmanager.presentation.navigation.Screen
import com.aryan.taskmanager.ui.theme.Green10
import com.aryan.taskmanager.ui.theme.Green20
import com.aryan.taskmanager.ui.theme.Green30
import com.aryan.taskmanager.ui.theme.Green40
import com.aryan.taskmanager.ui.theme.spotifyBG

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SignUpComposable(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG)
    ) {
        Text(
            text = "Task Manager",
            fontSize = 30.sp,
            color = Green30,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(70.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                maxLines = 1,
                value = name,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Green20,
                    focusedBorderColor = Green20,
                    unfocusedBorderColor = Green10,
                ),
                onValueChange = {
                    name = it
                },
                label = {
                    Text(
                        text = "Name",
                        color = Green30
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                maxLines = 1,
                value = email,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Green20,
                    focusedBorderColor = Green20,
                    unfocusedBorderColor = Green10,
                ),
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "Email",
                        color = Green30
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                maxLines = 1,
                value = pass,
                visualTransformation = if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Green20,
                    focusedBorderColor = Green20,
                    unfocusedBorderColor = Green10,
                ),
                onValueChange = {
                    pass = it
                },
                label = {
                    Text(
                        text = "Password",
                        color = Green30
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                        val icon =
                            if (isPasswordVisible) ImageVector.vectorResource(id = R.drawable.visibility_off_) else ImageVector.vectorResource(
                                id = R.drawable.baseline_visibility_24
                            )
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggable Password Visibility Option",
                            tint = if (isPasswordVisible) Green20 else Green30

                        )
                    }
                },
                keyboardActions = KeyboardActions {
                    isPasswordVisible = !isPasswordVisible
                }
            )
            Spacer(modifier = Modifier.height(70.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 30.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green40
                )
            ) {
                Text(
                    text = "Create Account",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already Have An Account!",
                    color = Green20
                )
                Spacer(modifier = Modifier.width(5.dp))
                TextButton(onClick = {
                    // Navigate to Login Screen...
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                    navController.navigate(NavigationRoute.LOGIN.route)
                }) {
                    Text(
                        text = "Login Now.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Green30
                    )
                }
            }
        }
    }
}