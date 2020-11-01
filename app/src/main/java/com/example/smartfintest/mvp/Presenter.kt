package com.example.smartfintest.mvp

import android.content.Context
import com.example.smartfintest.Post
import com.example.smartfintest.db.DBHelper
import com.example.smartfintest.db.RepositoryImpl
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Presenter(val mView: MVPView.View, val context: Context?): MVPView.Presenter {

    var mPost : MutableList<Post> = ArrayList()

    fun onLoadDataSet(city:String) {
              val dbHelper = DBHelper(context!!)
        dbHelper.create_db()

        RepositoryImpl().getRead(dbHelper,city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Post>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(posts: MutableList<Post>) {
                    mPost.addAll(posts)
                    mView.showData(mPost)

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                }

                override fun onComplete() {
                    mView.showComplete()
                }
            })
    }

    fun onLoadDataAll() {
        val dbHelper = DBHelper(context!!)
        dbHelper.create_db()

        RepositoryImpl().getReadAll(dbHelper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Post>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(posts: MutableList<Post>) {
                    mPost.addAll(posts)
                    mView.showData(mPost)

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                }

                override fun onComplete() {
                    mView.showComplete()
                }
            })

    }

    override fun onLoadData(position: Int) {
        when(position){
            0 -> onLoadDataAll()
            1 -> onLoadDataSet("Сочи")
            2 -> onLoadDataSet("Россия")
            3 -> onLoadDataSet("Белорусь")
        }
    }
}