package com.example.wikimvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wikimvvm.`interface`.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.FavouriteArticle
import com.example.wikimvvm.repository.ArticleRepository

class ArticleViewModel: ViewModel() {
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
    private val articleToSend = MutableLiveData<FavouriteArticle>()
    val articleModel = MutableLiveData<MutableList<ArticleResponse>>()
    val articleDAO = MutableLiveData<ArticleDAO>()

    fun newRandomListOfArticles(){
        viewModelListOfArticles.clear()
        repeat(10){
            val randomArticle = articleRepository.getRandomArticle()
            viewModelListOfArticles.add(randomArticle)
        }
        articleModel.postValue(viewModelListOfArticles)
    }

    fun receiveArticle(favouriteArticle: FavouriteArticle){
        articleToSend.postValue(favouriteArticle)
    }

    fun getArticle(): MutableLiveData<FavouriteArticle> = articleToSend

    fun receiveDAO(db: ArticleDAO){
        articleDAO.postValue(db)
    }

    fun getDAO(): ArticleDAO? {
        return this.articleDAO.value
    }

    fun insertFavouriteArticle(favouriteArticle: FavouriteArticle){
        getDAO()!!.insertFavouriteArticle(favouriteArticle)
    }

    fun getFavouriteArticles(): List<FavouriteArticle> {
        return getDAO()!!.getAll()
    }
}
