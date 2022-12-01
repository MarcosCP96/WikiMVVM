package com.example.wikimvvm.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.views.ArticleFragment

class FavouriteArticleAdapter(
    private val onCLick: (articleResponse: ArticleResponse) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {
    private var listOfFavouriteArticles: List<ArticleResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = listOfFavouriteArticles[position]
        holder.render(item) {
            onCLick.invoke(it)
        }
    }

    override fun getItemCount() = listOfFavouriteArticles.size

    fun changeList(list: List<ArticleResponse>) {
        listOfFavouriteArticles = list
        notifyDataSetChanged()
    }
}