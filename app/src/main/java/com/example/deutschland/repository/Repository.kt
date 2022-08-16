package com.example.deutschland.repository

import com.example.deutschland.api.RetrofitInstance
import com.example.deutschland.model.MainModel
import retrofit2.Response

class Repository {

    suspend fun getAllCourses() : Response<MainModel> {
        return RetrofitInstance.api.getAllCourses()
    }

}