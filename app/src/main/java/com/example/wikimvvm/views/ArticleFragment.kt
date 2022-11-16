package com.example.wikimvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wikimvvm.databinding.FragmentArticleBinding
import com.example.wikimvvm.repository.ArticleRepository


class ArticleFragment : Fragment() {
    private val articleRepository = ArticleRepository
    private var _binding:FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = articleRepository.getRandomArticle().title
        binding.tvExtract.text = articleRepository.getRandomArticle().extract
    }
}