package com.example.wikimvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
    private val articleToSend = MutableLiveData<ArticleResponse>()
//    private val articleDAO = MutableLiveData<ArticleDAO>()
    val articleModel = MutableLiveData<MutableList<ArticleResponse>>()
    lateinit var articleDAO: ArticleDAO

    fun newRandomListOfArticles(){
        viewModelListOfArticles.clear()
        CoroutineScope(Dispatchers.IO).launch {
            repeat(10){
                    val randomArticle = articleRepository.getRandomArticle()
                    viewModelListOfArticles.add(randomArticle)
            }
            articleModel.postValue(viewModelListOfArticles)
        }
    }

    fun receiveArticle(articleResponse: ArticleResponse){
        articleToSend.postValue(articleResponse)
    }

    fun getArticle(): MutableLiveData<ArticleResponse> = articleToSend

    fun receiveDAO(db: ArticleDAO){
        articleDAO = db
    }

//    private fun useDAO(): ArticleDAO {
//        return articleDAO.value!!
//    }

    fun insertFavouriteArticle(articleResponse: ArticleResponse){
        articleDAO.insertFavouriteArticle(articleResponse)
    }

//    fun getFavouriteArticles(): List<ArticleResponse> {
//        return useDAO().getAll()
//    }
}
