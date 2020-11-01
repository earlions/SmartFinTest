package com.example.smartfintest.mvp

import com.example.smartfintest.Post
import com.example.smartfintest.PostCkeck

interface MVPView {
    interface View{
        fun showData(posts: MutableList<Post>)
        fun showComplete()
        fun showError()
        fun hideProgress()
    }
    interface Presenter{
        fun onLoadData(city:Int)
    }
    interface ViewCheck{
        fun showNumberCheck(number:Int)
    }
    interface PresenterCheck{
        fun onLoadData()
        fun insertDara(mPost: MutableList<PostCkeck>, number: Int)
    }
}