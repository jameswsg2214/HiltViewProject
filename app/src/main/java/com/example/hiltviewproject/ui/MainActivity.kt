package com.example.hiltviewproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.hiltviewproject.R
import com.example.hiltviewproject.data.remote.NetworkResult
import com.example.hiltviewproject.databinding.ActivityMainBinding
import com.example.hiltviewproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var _binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        fetchData()
    }

    private fun fetchData() {
//        mainViewModel.getDogResponse()

        mainViewModel.response.observe(this){

            when(it){

                is NetworkResult.Error->{

                }
                is NetworkResult.Success->{

                    _binding.imgDog.load(
                        it.data?.message
                    ) {
                        transformations(RoundedCornersTransformation(16f))
                    }

                }
                is NetworkResult.Loading->{

                }


            }

        }
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.getDogResponse()


    }
}