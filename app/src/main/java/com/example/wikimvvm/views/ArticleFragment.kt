package com.example.wikimvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleBinding
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.Thumbnail
import com.example.wikimvvm.viewmodel.ArticleViewModel

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private var articleSent = ArticleResponse("", Thumbnail(""),"")
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable("articulo").let {
            articleSent = it as ArticleResponse
        }

        binding.tvTitle.text = articleSent.title
        binding.tvExtract.text = articleSent.extract
        articleSent.thumbnail?.let {
            Glide.with(this).load(it.source).into(binding.ivImage)
        }

        binding.backToMenuButton.setOnClickListener {
            val toTargetBTransaction = parentFragmentManager.beginTransaction()
            toTargetBTransaction.replace(R.id.placeholder, ArticleListFragment(), "articleFragment")
                .commit()
        }

        binding.addToFavouriteButton.setOnClickListener {
            articleViewModel.insertFavouriteArticle(requireContext() ,articleSent)
        }
    }
}