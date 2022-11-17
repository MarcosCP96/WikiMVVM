package com.example.wikimvvm.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleListBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.viewmodel.ArticleViewModel

class ArticleListFragment : Fragment() {
    private val articleRepository = ArticleRepository
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()

    private var listOfArticles = mutableListOf<ArticleResponse>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        repeat(10){
            listOfArticles.add(articleRepository.getRandomArticle())
        }
        articleViewModel.articleModel.observe(this, Observer { listInViewModel ->
            listOfArticles.clear()
            listInViewModel.forEach { articleInViewModel ->
                listOfArticles.add(articleInViewModel)
            }
        })
        binding.randomButton.setOnClickListener {
            articleViewModel.newRandomListOfArticles()
            binding.articleList.adapter = ArticleAdapter(listOfArticles)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.articleList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ArticleAdapter(listOfArticles)
    }
}

//        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
//        return view