package com.example.hiltviewproject.di

import android.content.Context
import androidx.room.Room
import com.example.hiltviewproject.dao.UserDao
import com.example.hiltviewproject.data.entity.User
import com.example.hiltviewproject.data.remote.DogService
import com.example.hiltviewproject.db.AppDataBase
import com.example.hiltviewproject.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideHttpLogging() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): DogService =
        retrofit.create(DogService::class.java)

    // room db
/*

    @Provides
    @Singleton
    fun providerAppDatabase(
        @ApplicationContext context: Context
    ):AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "NewDb"
        ).build()
    }

    @Provides
    @Singleton
    fun providerUserDao(appDataBase: AppDataBase):UserDao{
        return appDataBase.userDao()
    }
*/

}