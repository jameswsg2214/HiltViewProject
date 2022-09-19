package com.example.hiltviewproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltviewproject.data.entity.User
import com.example.hiltviewproject.data.model.DogResponse
import com.example.hiltviewproject.data.remote.NetworkResult
import com.example.hiltviewproject.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

    private val _response:MutableLiveData<NetworkResult<DogResponse>> = MutableLiveData()

    val response :LiveData<NetworkResult<DogResponse>> = _response



    fun getDogResponse() = viewModelScope.launch {
        repository.getDog().collect{ values ->

            _response.value = values
        }
    }

    fun addDetails(user: User)= viewModelScope.launch {

        withContext(Dispatchers.IO){

            repository.insertUserDetails(user)
        }
    }

    private val _listResponse:MutableLiveData<List<User>> = MutableLiveData()

    val listResponse :LiveData<List<User>> = _listResponse



    fun getAllDetails() {
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                _listResponse.value=repository.getAllDetails()
            }
        }
    }




}