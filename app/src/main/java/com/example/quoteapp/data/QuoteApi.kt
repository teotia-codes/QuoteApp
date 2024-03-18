package com.example.quoteapp.data

import com.example.quoteapp.domain.model.Quote
import com.example.quoteapp.util.Constant
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET(Constant.END_POINT)
    suspend fun getQuote() :Response<Quote>
}