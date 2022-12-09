package com.example.wikimvvm.viewmodel

import android.content.Context
import android.widget.Toast
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

    fun addArticleToFavourite(toast: Toast, articleResponse: ArticleResponse){
        CoroutineScope(Dispatchers.IO).launch {
            if (checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse)) {
                insertFavouriteArticleUseCase.insertFavouriteArticle(articleResponse)
                toast.show()
            } else {
                toast.setText("${articleResponse.title} ya está en favoritos")
                toast.show()
            }
        }
    }

    fun emptyListOfFavourites(toast: Toast) {
        CoroutineScope(Dispatchers.IO).launch {
            toast.show()
            emptyListOfFavouritesUseCase.emptyListOfFavourites()
        }
    }

    fun deleteArticleFromFavourites(toast: Toast, articleToDelete: ArticleResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleToDelete)){
                toast.show()
                deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(articleToDelete)
            } else {
                toast.setText("${articleToDelete.title} no está en favoritos")
                toast.show()
            }
        }
    }
}

