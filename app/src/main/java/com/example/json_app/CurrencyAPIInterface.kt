package com.example.json_app

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPIInterface {
    @GET("eur.json")
    fun getCurrency(): Call<Currency>
}