package com.example.hiltviewproject.data.repository

import com.example.hiltviewproject.data.remote.DogService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val dogService: DogService){

    suspend fun getDog() = dogService.getDog()

}