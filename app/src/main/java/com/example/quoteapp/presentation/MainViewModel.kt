package com.example.quoteapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteapp.data.RemoteDataRepository
import com.example.quoteapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository
): ViewModel(){
    val response: MutableState<ApiState> = mutableStateOf(ApiState.Idle)
    fun getRandomQuote() = viewModelScope.launch {
        remoteDataRepository.getQuote()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect{
                response.value = ApiState.Success(it)
            }
    }
}