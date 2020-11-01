package com.example.smartfintest.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfintest.PostCkeck
import com.example.smartfintest.R
import com.example.smartfintest.adapter.RecyclerChechAdapter
import com.example.smartfintest.mvp.MVPView
import com.example.smartfintest.mvp.PresenterCheck
import kotlinx.android.synthetic.main.fragment_check.*
import java.util.ArrayList

class CheckFragment : Fragment(),MVPView.ViewCheck{
    var mPosts: MutableList<PostCkeck> = ArrayList()
    var number:Int=0
    var summa: Double=0.00
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_check, container, false)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        val buttonClearCheck = view.findViewById(R.id.buttonClearCheck) as Button
        val buttonCheck = view.findViewById(R.id.buttonCheck) as Button
        val textViewSum = view.findViewById(R.id.textViewSum) as TextView
        val tvNumberCheck = view.findViewById(R.id.tvNumberCheck) as TextView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = RecyclerChechAdapter(mPosts)
        buttonClearCheck.setOnClickListener(){
            mPosts.clear()
            recyclerView.adapter?.notifyDataSetChanged()
            buttonCheck.setBackgroundColor(Color.WHITE)
            buttonCheck.setTextColor(resources.getColor(R.color.grey))
        }
        val presenter = PresenterCheck(this, context)
        presenter.onLoadData()
        buttonCheck.setOnClickListener(){
            presenter.insertDara(mPosts,number++)
            mPosts.clear()
            recyclerView.adapter?.notifyDataSetChanged()
            buttonCheck.setBackgroundColor(Color.WHITE)
            buttonCheck.setTextColor(resources.getColor(R.color.grey))
            tvNumberCheck.text = "#"+number
        }
        return view
    }


    fun setText(name: String, price: Double, size: Double){
        val post = PostCkeck(name,price,size)
        summa+=price*size
        mPosts.add(post)
        recyclerView.adapter?.notifyDataSetChanged()
        buttonCheck.setBackgroundColor(resources.getColor(R.color.teal_200))
        buttonCheck.setTextColor(Color.BLACK)
        textViewSum.text = summa.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun showNumberCheck(numb: Int) {
        if (numb!=0)
            number=numb+1
        tvNumberCheck.text = "#"+number
    }

}