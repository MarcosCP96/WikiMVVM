package com.example.wikimvvm.model

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wikimvvm.R

class ArticleViewHolder(view: View) : ViewHolder(view) {
    private val article = view.findViewById<TextView>(R.id.tvTitulo)

    fun render(articleResponse: ArticleResponse, onCLick: (articleResponse: ArticleResponse) -> Unit) {
        article.text = articleResponse.title
        article.setOnClickListener { onCLick(articleResponse) }
    }
}