package com.example.wikimvvm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleListBinding
import com.example.wikimvvm.model.FavouriteArticle
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.viewmodel.ArticleViewModel

class FavouriteArticlesFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()
    private var listOfFavouriteArticles = mutableListOf<FavouriteArticle>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        listOfFavouriteArticles = articleViewModel.getFavouriteArticles() as MutableList
        listOfFavouriteArticles.forEach {
            println(it)
        }
        return binding.root
    }

}