package com.example.smartfintest.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfintest.Post
import com.example.smartfintest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_home.view.*

class RecyclerRestAdapter internal constructor(private var mPosts: MutableList<Post>, private  var view: OnFragmentClick): RecyclerView.Adapter<RecyclerRestAdapter.TestHolder>() {

    class TestHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindCrime(mPost: Post) {
            itemView.textView_home.append(mPost.name)
            Picasso.get().load(mPost.image).fit().centerCrop().into(itemView.image_crime)

            when(mPost.country){
                "Сочи"-> itemView.card_view.setBackgroundColor(Color.BLUE)
                "Россия"-> itemView.card_view.setBackgroundColor(Color.CYAN)
                "Белорусь"-> itemView.card_view.setBackgroundColor(Color.YELLOW)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_home,parent,false)
        return TestHolder(view)
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        val post = mPosts[position]
        holder.bindCrime(post)
        holder.itemView.rl_sitt.setOnClickListener(){
            view.onFragmentClick(post.name,post.price.toString())
        }

    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}