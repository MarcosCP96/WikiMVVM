package com.example.wikimvvm.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.Thumbnail
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.repository.ArticleRepository
import kotlinx.coroutines.*

class ArticleViewModel() : ViewModel() {
    private val articleRepository = ArticleRepository
    private val viewModelListOfArticles = mutableListOf<ArticleResponse>()
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

    private fun insertFavouriteArticle(context: Context, articleResponse: ArticleResponse) {
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            db.articleDao().insertFavouriteArticle(articleResponse)
        }
        db.close()
    }

    fun checkIfArticleInDb(context: Context, articleResponse: ArticleResponse) {
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            if (db.articleDao().getArticle(articleResponse.title) == null) {
                insertFavouriteArticle(context, articleResponse)
            }
        }
        db.close()
    }

    fun emptyListOfFavourites(context: Context) {
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            db.articleDao().emptyFavouriteList()
        }
        db.close()
    }

    fun deleteArticleFromFavourites(context: Context, articleToDelete: ArticleResponse) {
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            db.articleDao().deleteArticle(articleToDelete)
        }
        db.close()
    }
}

