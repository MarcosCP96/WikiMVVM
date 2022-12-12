package com.example.wikimvvm.views

import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleListBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.viewmodel.ArticleViewModel
import com.example.wikimvvm.viewmodel.ViewModelFactory

class ArticleListFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels { ViewModelFactory(requireContext()) }

    private var listOfArticles = mutableListOf<ArticleResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        binding.articleList.layoutManager = LinearLayoutManager(this.context)
        val adapter = ArticleAdapter {
            val fragment = ArticleFragment().apply {
                arguments = Bundle().apply { putSerializable("articulo", it) }
            }
            val toSelectedArticle = parentFragmentManager.beginTransaction()
            toSelectedArticle.add(
                R.id.placeholder,
                fragment
            ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }

        binding.articleList.adapter = adapter

        articleViewModel.articleModel.observe(viewLifecycleOwner) { listInViewModel ->
            adapter.changeList(listInViewModel)
            listOfArticles = listInViewModel
        }

        articleViewModel.newRandomListOfArticles()

        binding.randomButton.setOnClickListener {
            articleViewModel.newRandomListOfArticles()
        }

        binding.toFavourites.setOnClickListener {
            val toSavedArticles = parentFragmentManager.beginTransaction()
            toSavedArticles.add(
                R.id.placeholder,
                FavouriteArticlesFragment(),
            ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}