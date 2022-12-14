package com.example.wikimvvm.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wikimvvm.R

class ArticleViewHolder(view: View) : ViewHolder(view) {
    private val articleItem = view.findViewById<CardView>(R.id.cardview)
    private val article = view.findViewById<TextView>(R.id.tvTitulo)
    private val articleImage = view.findViewById<ImageView>(R.id.ivItemImage)

    fun render(articleResponse: ArticleResponse, onClick: (articleResponse: ArticleResponse) -> Unit) {
        article.text = articleResponse.title
        articleResponse.thumbnail?.let {
            Glide.with(this.itemView).load(it.source).into(articleImage)
        }
        article.setOnClickListener { onClick(articleResponse) }
    }
}