package com.example.simplemvvm.api

import com.example.simplemvvm.models.ResponseApi
import com.example.simplemvvm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT_GET_CHARACTER)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<ResponseApi>
}