package com.aryan.taskmanager.presentation.login_screen

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
class LoginScreenViewModel @Inject constructor(
    private val repository: SeverRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    fun onEvent(events: LoginUiEvents) {
        when(events) {
            is LoginUiEvents.LoginEmailChanged -> {
                state = state.copy(loginEmail = events.value)
            } is LoginUiEvents.LoginPasswordChanged -> {
                state = state.copy(loginPassword = events.value)
            } is LoginUiEvents.Login -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                email = state.loginEmail,
                password = state.loginPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

}