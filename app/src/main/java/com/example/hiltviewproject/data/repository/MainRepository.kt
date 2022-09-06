package com.example.hiltviewproject.data.repository

import com.example.hiltviewproject.data.model.DogResponse
import com.example.hiltviewproject.data.remote.BaseApiResponse
import com.example.hiltviewproject.data.remote.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
):BaseApiResponse(){

    suspend fun getDog() :Flow<NetworkResult<DogResponse>>{
        return flow {
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }

}