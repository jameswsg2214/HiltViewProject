package com.example.hiltviewproject.data.remote

import com.example.hiltviewproject.data.model.DogResponse
import com.example.hiltviewproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface DogService {

    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>

}