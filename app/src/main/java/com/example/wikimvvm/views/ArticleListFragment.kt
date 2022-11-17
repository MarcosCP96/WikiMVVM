package com.example.wikimvvm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository

class ArticleListFragment : Fragment() {
    private val articleRepository = ArticleRepository
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val listOfArticles = mutableListOf<ArticleResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        repeat(10){
            listOfArticles.add(articleRepository.getRandomArticle())
        }
        val randomButton = view.findViewById<Button>(R.id.randomButton).setOnClickListener {
            listOfArticles.clear()
            repeat(10){
                listOfArticles.add(articleRepository.getRandomArticle())
            }
            val recyclerView = view.findViewById<RecyclerView>(R.id.articleList)
            recyclerView.adapter = ArticleAdapter(listOfArticles)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.articleList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ArticleAdapter(listOfArticles)
    }
}