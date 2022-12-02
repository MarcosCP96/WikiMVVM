package com.example.wikimvvm.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R

class ArticleAdapter(
    private val onCLick: (articleResponse: ArticleResponse) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {
    var listOfArticles: List<ArticleResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = listOfArticles[position]
        holder.render(item) {
            onCLick.invoke(it)
        }
    }

    override fun getItemCount() = listOfArticles.size

    fun changeList(listInViewModel: List<ArticleResponse>) {
        listOfArticles = listInViewModel.filterNotNull()
        notifyDataSetChanged()
    }
}