package com.example.wikimvvm.model

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.views.ArticleListFragment

class ArticleAdapter(private val listOfArticles: List<ArticleResponse>): RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = listOfArticles[position]
        holder.render(item)
//        holder.itemView.setOnClickListener {
//            item.title = "Prueba"
//        }
    }

    override fun getItemCount() = listOfArticles.size
}