package com.example.quoteapp.util

import com.example.quoteapp.data.QuoteApi
import com.example.quoteapp.domain.model.Quote
import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<Quote>): ApiState()
    data class Failure(val error: Throwable) : ApiState()
    data object Loading : ApiState()
    data object Idle : ApiState()
}