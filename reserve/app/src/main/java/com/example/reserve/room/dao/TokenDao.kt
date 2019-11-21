package com.example.reserve.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.reserve.base.BaseDao
import com.example.reserve.room.model.Token
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TokenDao : BaseDao<Token> {

    @Query("Select * From TokenTable")
    fun getToken() : Single<Token>

    @Query("Delete From TokenTable")
    fun deleteToken() : Completable
}