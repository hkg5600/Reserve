package com.example.reserve.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reserve.room.dao.TokenDao
import com.example.reserve.room.model.Token

@Database(entities = [Token::class], version = 1, exportSchema = false)
abstract class TokenDatabase : RoomDatabase() {
    abstract fun tokenDao() : TokenDao

    companion object {
        private var INSTANCE : TokenDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TokenDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TokenDatabase::class.java, "token_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}