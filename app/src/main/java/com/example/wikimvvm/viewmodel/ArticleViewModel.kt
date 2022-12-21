package com.example.wikimvvm.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.NetworkRequest
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.repository.ArticleRepository
import com.example.wikimvvm.useCase.*
import kotlinx.coroutines.*

class ArticleViewModel(context: Context) : ViewModel() {
    private var db: ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java, "articlesDB"
    ).fallbackToDestructiveMigration().build()
    private val networkRequest = NetworkRequest()
    private val articleRepository = ArticleRepository(networkRequest)
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

    fun isArticleInFavourites(articleResponse: ArticleResponse): Boolean{
        return checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse)
    }

    fun addArticleToFavourite(articleResponse: ArticleResponse){
        CoroutineScope(Dispatchers.IO).launch {
            if (checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse)) {
                insertFavouriteArticleUseCase.insertFavouriteArticle(articleResponse)
//                toast.show()
            } else {
//                toast.setText("${articleResponse.title} ya está en favoritos")
//                toast.show()
            }
        }
    }

    fun emptyListOfFavourites() {
        CoroutineScope(Dispatchers.IO).launch {
//            toast.show()
            emptyListOfFavouritesUseCase.emptyListOfFavourites()
        }
    }

    fun deleteArticleFromFavourites(articleToDelete: ArticleResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleToDelete)){
                deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(articleToDelete)
            }
        }
    }
}

