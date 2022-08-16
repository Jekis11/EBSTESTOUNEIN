package com.example.deutschland.api

import com.example.deutschland.model.MainModel
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitAPI {
    @GET("/products")
    suspend fun getAllCourses() : Response<MainModel>
}
