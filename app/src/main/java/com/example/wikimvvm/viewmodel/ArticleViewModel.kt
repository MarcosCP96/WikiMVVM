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

class ArticleViewModel(context: Context) : ViewModel() {
    private var db: ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java, "articlesDB"
    ).build()
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
    private val checkIfArticleInFavouritesUseCase = CheckIfArticleInFavouritesUseCase(db.articleDao())
    private val emptyListOfFavouritesUseCase = EmptyListOfFavouritesUseCase(db.articleDao())
    private val deleteArticleFromFavouritesUseCase = DeleteArticleFromFavouritesUseCase(db.articleDao())
    private val insertFavouriteArticleUseCase = InsertFavouriteArticleUseCase(db.articleDao())
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

    fun checkIfArticleInDb(articleResponse: ArticleResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            if (checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse)) {
                insertFavouriteArticleUseCase.insertFavouriteArticle(articleResponse)
            }
        }
    }

    fun emptyListOfFavourites() {
        CoroutineScope(Dispatchers.IO).launch {
            emptyListOfFavouritesUseCase.emptyListOfFavourites()
        }
    }

    fun deleteArticleFromFavourites(articleToDelete: ArticleResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(articleToDelete)
        }
    }

}

