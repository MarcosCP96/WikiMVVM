package com.example.wikimvvm.useCase

import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.viewmodel.ArticleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetRandomArticlesUseCase {
//    private var articleRepository = ArticleRepository
//    private val articleViewModel: ArticleViewModel by viewModels()
//
//    fun getRandomArticlesList(viewModelListOfArticles: MutableList<ArticleResponse>){
//        viewModelListOfArticles.clear()
//        repeat(10) {
//            CoroutineScope(Dispatchers.IO).launch {
//                val randomArticle = articleRepository.getRandomArticle()
//                viewModelListOfArticles.add(randomArticle)
//                articleViewModel.articleModel.postValue(viewModelListOfArticles)
//            }
//        }
//    }
}