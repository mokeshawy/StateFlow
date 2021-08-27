package com.example.stateflow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stateflow.databinding.ItemListUsBinding
import com.example.stateflow.onclickinterface.OnClick
import com.example.stateflow.response.Article
import com.example.stateflow.utils.Utils.getProgressDrawable
import com.example.stateflow.utils.Utils.loadImage

class TeslaAdapter (private val dataSet: ArrayList<Article> , var teslaOnClick: OnClick) : RecyclerView.Adapter<TeslaAdapter.ViewHolder>() {

    private lateinit var binding : ItemListUsBinding

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun initialize(viewHolder: ViewHolder, dataSet: Article, action: OnClick) {
            action.teslaOnClick(viewHolder, dataSet, adapterPosition)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        binding = ItemListUsBinding.inflate(LayoutInflater.from(viewGroup.context),)
        return ViewHolder(binding.root)

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val tesla = dataSet[position]

        // contents of the view with that element
        binding.tvLatestNewsItem.text = tesla.title
        binding.ivItemLatestNews.loadImage(tesla.urlToImage , getProgressDrawable(viewHolder.itemView.context))

        viewHolder.initialize(viewHolder , tesla , teslaOnClick)

    }

    // Return the size of your dataSet (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Interface on click
//    interface OnClickAppleNews {
//        fun onClickAppleNews(viewHolder: ViewHolder, dataSet: Article, position: Int)
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Article>) {
        dataSet.clear()
        dataSet.addAll(list)
        notifyDataSetChanged()
    }
}