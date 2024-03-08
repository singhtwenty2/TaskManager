package com.aryan.taskmanager.presentation.signup_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryan.taskmanager.data.api.repository.SeverRepository
import com.aryan.taskmanager.data.api.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repository: SeverRepository
) : ViewModel() {

    var state by mutableStateOf(SignUpState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    fun onEvent(events: SignUpUiEvents) {
        when (events) {
            is SignUpUiEvents.SignUpNameChanged -> {
                state = state.copy(signUpName = events.value)
            }

            is SignUpUiEvents.SignUpEmailChanged -> {
                state = state.copy(signUpEmail = events.value)
            }

            is SignUpUiEvents.SignUpPasswordChanged -> {
                state = state.copy(signUpPassword = events.value)
            }

            is SignUpUiEvents.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                name = state.signUpName,
                email = state.signUpEmail,
                password = state.signUpPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

}