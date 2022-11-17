package com.example.wikimvvm.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository


class ArticleFragment : Fragment() {
    private val articleRepository = ArticleRepository
    private var _binding:FragmentArticleBinding? = null
    private val binding get() = _binding!!

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentArticleBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOfArticles = mutableListOf<ArticleResponse>()
        repeat(10){
            listOfArticles.add(articleRepository.getRandomArticle())
        }
        initRecycler(listOfArticles)
    }
    private fun initRecycler(listOfArticles: MutableList<ArticleResponse>) {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.articleList)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ArticleAdapter(listOfArticles)
    }
}