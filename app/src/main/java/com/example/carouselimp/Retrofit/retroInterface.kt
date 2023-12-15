package com.example.carouselimp.Retrofit
import retrofit2.http.GET
interface retroInterface {
    @GET("db.json")
    suspend fun getTest():retrofit2.Response<Test>


}