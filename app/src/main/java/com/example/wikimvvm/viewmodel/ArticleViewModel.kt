package com.example.wikimvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository

class ArticleViewModel: ViewModel() {
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
    private val articleToSend = MutableLiveData<ArticleResponse>()
    val articleModel = MutableLiveData<MutableList<ArticleResponse>>()

    fun newRandomListOfArticles(){
        viewModelListOfArticles.clear()
        repeat(10){
            val randomArticle = articleRepository.getRandomArticle()
            viewModelListOfArticles.add(randomArticle)
        }
        articleModel.postValue(viewModelListOfArticles)
    }

    fun getArticle(articleResponse: ArticleResponse){
        articleToSend.postValue(articleResponse)
    }
}
