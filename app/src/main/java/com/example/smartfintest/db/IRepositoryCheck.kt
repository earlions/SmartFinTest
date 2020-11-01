package com.example.smartfintest.db

import com.example.smartfintest.Post
import com.example.smartfintest.PostCkeck
import io.reactivex.Observable

interface IRepositoryCheck {
    fun getCount(dbHelper: DBHelperCH): Observable<Int>
    fun insertDataCheck(dbHelper: DBHelperCH,  number: Int, mPosts: PostCkeck): Observable<Int>
}