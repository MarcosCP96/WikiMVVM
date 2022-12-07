package com.example.wikimvvm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentFavouriteArticlesBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.viewmodel.ArticleViewModel
import com.example.wikimvvm.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteArticlesFragment : Fragment() {
    private var _binding: FragmentFavouriteArticlesBinding? = null
    private val binding get() = _binding!!
//    private val articleViewModel: ArticleViewModel by viewModels()
    private val articleViewModel: ArticleViewModel by viewModels { ViewModelFactory(requireContext()) }
    private var listOfArticles = listOf<ArticleResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteArticlesBinding.inflate(inflater, container, false)
        binding.favouriteArticlesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val db = Room.databaseBuilder(
            requireContext(),
            ArticleDatabase::class.java, "articlesDB"
        ).fallbackToDestructiveMigration().build()

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

        binding.favouriteArticlesRecyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            listOfArticles = db.articleDao().getAll()
            activity?.runOnUiThread {
                adapter.changeList(listOfArticles)
            }
        }

        binding.backToMenuButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.emptyListButton.setOnClickListener {
            articleViewModel.emptyListOfFavourites()
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}