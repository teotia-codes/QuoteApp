package com.example.quoteapp.data

import com.example.quoteapp.domain.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
    private val quoteApi: QuoteApi
){
    fun getQuote(): Flow<Response<Quote>> = flow {
        emit(quoteApi.getQuote())
    }.flowOn(Dispatchers.IO)
}