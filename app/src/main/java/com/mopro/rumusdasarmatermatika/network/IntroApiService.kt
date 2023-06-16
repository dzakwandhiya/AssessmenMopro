package com.mopro.rumusdasarmatermatika.network

import com.mopro.rumusdasarmatermatika.model.Intro
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://raw.githubusercontent.com/dzakwandhiya/AssessmenMopro/api/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface IntroApiService {
    @GET("api.json")
    suspend fun getApiData(): List<Intro>
    // Tambahkan endpoint lain yang diperlukan di sini
}
object IntroApi{
    val service: IntroApiService by lazy {
        retrofit.create(IntroApiService::class.java)
    }
}
