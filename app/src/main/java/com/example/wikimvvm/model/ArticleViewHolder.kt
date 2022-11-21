package com.example.wikimvvm.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wikimvvm.R

class ArticleViewHolder(view: View) : ViewHolder(view) {
    private val article = view.findViewById<TextView>(R.id.tvTitulo)
    private val articleImage = view.findViewById<ImageView>(R.id.ivItemImage)

    fun render(articleResponse: ArticleResponse, onCLick: (articleResponse: ArticleResponse) -> Unit) {
        article.text = articleResponse.title
        Glide.with(this.itemView).load(articleResponse.thumbnail.source).into(articleImage)
        article.setOnClickListener { onCLick(articleResponse) }
    }
}