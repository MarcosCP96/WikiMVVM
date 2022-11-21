package com.example.wikimvvm.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikimvvm.R
import com.example.wikimvvm.databinding.FragmentArticleListBinding
import com.example.wikimvvm.model.ArticleAdapter
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.viewmodel.ArticleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
//        binding.articleList.setHasFixedSize(true)
        binding.articleList.layoutManager = LinearLayoutManager(this.context)
        articleViewModel.newRandomListOfArticles()

        val adapter = ArticleAdapter(parentFragmentManager) {
            ArticleFragment().apply { arguments = Bundle().apply { putSerializable("articulo", it) } }
        }
        binding.articleList.adapter = adapter

        articleViewModel.articleModel.observe(viewLifecycleOwner, Observer { listInViewModel ->
            adapter.changeList(listInViewModel)
        })

        binding.randomButton.setOnClickListener {
            articleViewModel.newRandomListOfArticles()
//            binding.articleList.adapter = ArticleAdapter(listOfArticles, parentFragmentManager) {
//            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
