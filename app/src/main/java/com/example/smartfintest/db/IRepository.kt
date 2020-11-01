package com.example.smartfintest.db

import com.example.smartfintest.Post
import io.reactivex.Observable

interface IRepository {
    fun getRead(dbHelper: DBHelper, city: String): Observable<MutableList<Post>>
    fun getReadAll(dbHelper: DBHelper): Observable<MutableList<Post>>
}