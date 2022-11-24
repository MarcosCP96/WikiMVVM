package com.example.wikimvvm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentFavouriteArticlesBinding
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.FavouriteArticleAdapter
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.viewmodel.ArticleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteArticlesFragment : Fragment() {
    private var _binding: FragmentFavouriteArticlesBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()
    private var listOfArticles = listOf<ArticleResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteArticlesBinding.inflate(inflater, container, false)
        binding.favouriteArticlesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val db = Room.databaseBuilder(
            requireContext(),
            ArticleDatabase::class.java, "articlesDB"
        ).build()

        val adapter = FavouriteArticleAdapter(parentFragmentManager) {
            FavouriteArticlesFragment().apply {
                arguments = Bundle().apply { putSerializable("articulo", it) }
            }
        }

        binding.favouriteArticlesRecyclerView.adapter = adapter
        binding.backToMenuButton.setOnClickListener {
            val toTargetBTransaction = parentFragmentManager.beginTransaction()
            toTargetBTransaction.replace(R.id.placeholder, ArticleListFragment(), "articleFragment")
                .commit()
        }
        return binding.root
    }
}