package com.example.wikimvvm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.useCase.*
import kotlinx.coroutines.*

class ArticleViewModel : ViewModel() {
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
    private val checkIfArticleInFavouritesUseCase = CheckIfArticleInFavouritesUseCase()
    private val emptyListOfFavouritesUseCase = EmptyListOfFavouritesUseCase()
    private val deleteArticleFromFavouritesUseCase = DeleteArticleFromFavouritesUseCase()
    var articleModel = MutableLiveData<MutableList<ArticleResponse>>()

    fun newRandomListOfArticles() {
        viewModelListOfArticles.clear()
        repeat(10) {
            CoroutineScope(Dispatchers.IO).launch {
                val randomArticle = articleRepository.getRandomArticle()
                viewModelListOfArticles.add(randomArticle)
                articleModel.postValue(viewModelListOfArticles)
            }
        }
    }

    fun checkIfArticleInDb(context: Context, articleResponse: ArticleResponse) {
        checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(context, articleResponse)
    }

    fun emptyListOfFavourites(context: Context) {
        emptyListOfFavouritesUseCase.emptyListOfFavourites(context)
    }

    fun deleteArticleFromFavourites(context: Context, articleToDelete: ArticleResponse) {
        deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(context, articleToDelete)
    }

    private fun insertFavouriteArticle(context: Context, articleResponse: ArticleResponse) {
    }
}

