package com.example.hiltviewproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hiltviewproject.dao.UserDao
import com.example.hiltviewproject.data.entity.User
import dagger.hilt.android.qualifiers.ApplicationContext
import it.czerwinski.android.hilt.annotations.FactoryMethod
import javax.inject.Singleton

@Database(entities = [User::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    @FactoryMethod
    @Singleton
    abstract fun userDao(): UserDao


    object Factory {

        @FactoryMethod
        @Singleton
        fun create(
            @ApplicationContext context: Context
        ): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "blog.db")
                .build()
    }
}