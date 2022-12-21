package com.example.wikimvvm.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleBinding
import com.example.wikimvvm.model.*
import com.example.wikimvvm.viewmodel.ArticleViewModel
import com.example.wikimvvm.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private var articleSent = ArticleResponse("", Thumbnail(""), "", ContentURLs(Mobile("")))
    private val articleViewModel: ArticleViewModel by viewModels { ViewModelFactory(requireContext()) }

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

        binding.articleBackToMenuButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.addToFavouriteButton.setOnClickListener {
            articleViewModel.addArticleToFavourite(articleSent)
        }

        binding.deleteButton.setOnClickListener {
            articleViewModel.deleteArticleFromFavourites(articleSent)
            parentFragmentManager.popBackStack()
        }

        binding.toUrlButton.setOnClickListener {
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(articleSent.content_urls.mobile.page)
//            startActivity(openURL)
            val fragment = WebFragment().apply {
                arguments = Bundle().apply { putSerializable("articulo", articleSent) }
            }
            val toWikipedia = parentFragmentManager.beginTransaction()
            toWikipedia.add(
                R.id.placeholder,
                fragment
            ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
    }
}