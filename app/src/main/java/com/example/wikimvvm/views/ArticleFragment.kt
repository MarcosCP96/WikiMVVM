package com.example.wikimvvm.views

import android.os.Bundle
import android.util.Log
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
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.viewmodel.ArticleViewModel


class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private var articleSent = ArticleResponse("", Thumbnail(""),"")

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
        Glide.with(this).load(articleSent.thumbnail.source).into(binding.ivImage)

        binding.backToMenuButton.setOnClickListener {
            val toTargetBTransaction = parentFragmentManager.beginTransaction()
            toTargetBTransaction.replace(R.id.placeholder, ArticleListFragment(), "articleFragment")
                .commit()
        }
    }
}