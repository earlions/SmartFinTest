package com.example.smartfintest.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfintest.Post
import com.example.smartfintest.R
import com.example.smartfintest.adapter.OnFragmentClick
import com.example.smartfintest.adapter.RecyclerRestAdapter
import com.example.smartfintest.mvp.MVPView
import com.example.smartfintest.mvp.Presenter
import kotlinx.android.synthetic.main.fragment_range.*
import java.util.*


class RangeFragment : Fragment(),MVPView.View, OnFragmentClick{

    private lateinit var mListener: OnFragmentInteractionListener
    var mPosts: MutableList<Post> = ArrayList()

    fun newInstance(position: Int): RangeFragment{
        val args = Bundle()
        args.putInt("ps", position)
        val fragment = RangeFragment()
        fragment.arguments=args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pos = arguments?.getInt("ps", 0)
        val view: View = inflater.inflate(R.layout.fragment_range, container, false)
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity,4)
        recyclerView.isNestedScrollingEnabled = false

        recyclerView.adapter = RecyclerRestAdapter(mPosts,this)
       val presenter = Presenter(this, context)
       presenter.onLoadData(pos!!)
        return view
    }
    internal interface OnFragmentInteractionListener {
        fun onFragmentInteraction(link: String,price:String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                    .toString() + " должен реализовывать интерфейс OnFragmentInteractionListener"
            )
        }
    }



    override fun onFragmentClick(name: String, price: String) {
        mListener.onFragmentInteraction(name, price)
    }

    override fun showData(posts: MutableList<Post>) {
        mPosts.clear()
        mPosts.addAll(posts)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showComplete() {

    }

    override fun showError() {

    }

    override fun hideProgress() {

    }


}