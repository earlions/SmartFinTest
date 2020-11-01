package com.example.smartfintest.db

import com.example.smartfintest.Post
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.*

class RepositoryImpl: IRepository {
    override fun getRead(dbHelper: DBHelper, city: String): Observable<MutableList<Post>> {
        return Observable.create<MutableList<Post>> { observableEmitter: ObservableEmitter<MutableList<Post>> ->
            val posts: MutableList<Post> = ArrayList()
            try {
                posts.addAll(dbHelper.getData(city))
                observableEmitter.onNext(posts)
            } finally {
                observableEmitter.onComplete()
            }
        }
    }

    override fun getReadAll(dbHelper: DBHelper): Observable<MutableList<Post>> {
        return Observable.create<MutableList<Post>> { observableEmitter: ObservableEmitter<MutableList<Post>> ->
            val posts: MutableList<Post> = ArrayList()
            try {
                posts.addAll(dbHelper.getDataAll())
                observableEmitter.onNext(posts)
            } finally {
                observableEmitter.onComplete()
            }
        }
    }
}