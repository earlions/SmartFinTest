package com.example.smartfintest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfintest.PostCkeck
import com.example.smartfintest.R
import kotlinx.android.synthetic.main.list_item_check.view.*
import kotlinx.android.synthetic.main.list_item_home.view.*
import kotlinx.android.synthetic.main.list_item_home.view.rl_sitt
import kotlinx.android.synthetic.main.list_item_home.view.textView_home

class RecyclerChechAdapter internal constructor(private var mPosts: MutableList<PostCkeck>): RecyclerView.Adapter<RecyclerChechAdapter.CheckHolder>() {
    class CheckHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindCrime(mPost: PostCkeck) {
            itemView.textView_home.text=mPost.name
            itemView.textViewOnePrice.text=mPost.price.toString()
            itemView.textViewPriceSum.text = "${mPost.size}x${mPost.price}"
            val sum:Double =  mPost.size*mPost.price
            itemView.textViewPrice.text=sum.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_check,parent,false)
        return CheckHolder(view)
    }

    override fun onBindViewHolder(holder: CheckHolder, position: Int) {
        val post = mPosts[position]
        holder.bindCrime(post)
        holder.itemView.rl_sitt.setOnClickListener(){

        }

    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}