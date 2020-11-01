package com.example.smartfintest.mvp

import android.content.Context
import com.example.smartfintest.Post
import com.example.smartfintest.PostCkeck
import com.example.smartfintest.db.DBHelper
import com.example.smartfintest.db.DBHelperCH
import com.example.smartfintest.db.RepositoryCheck
import com.example.smartfintest.db.RepositoryImpl
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterCheck(val mView: MVPView.ViewCheck, val context: Context?):MVPView.PresenterCheck {

    override fun onLoadData() {
        val dbHelper = DBHelperCH(context!!)

        RepositoryCheck().getCount(dbHelper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(number: Int) {
                    mView.showNumberCheck(number)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                }

                override fun onComplete() {
                }
            })
    }

    override fun insertDara(mPosts: MutableList<PostCkeck>, number: Int) {
        val dbHelper = DBHelperCH(context!!)
        val post = mPosts
        for (i in 0 until post.size)
        RepositoryCheck().insertDataCheck(dbHelper,number,post[i])
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(number: Int) {

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                }

                override fun onComplete() {
                }
            })
    }
}