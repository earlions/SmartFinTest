package com.example.smartfintest.db

import com.example.smartfintest.PostCkeck
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class RepositoryCheck:IRepositoryCheck {
    override fun getCount(dbHelper: DBHelperCH): Observable<Int> {
        return Observable.create<Int> { observableEmitter: ObservableEmitter<Int> ->

            try {
                val number = dbHelper.getCount()

                observableEmitter.onNext(number)
            } finally {
                observableEmitter.onComplete()
            }
        }
    }

    override fun insertDataCheck(dbHelper: DBHelperCH, number: Int, mPosts: PostCkeck):Observable<Int> {


        return Observable.create<Int> { observableEmitter: ObservableEmitter<Int> ->
            try {
                dbHelper.insertCheck(mPosts,number)
                observableEmitter.onNext(number)
            } finally {
                observableEmitter.onComplete()
            }
        }
    }
}