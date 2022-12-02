package com.example.wikimvvm.useCase

import android.content.Context
import androidx.room.Room
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.repository.ArticleDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertFavouriteArticleUseCase {
    fun insertFavouriteArticle(context: Context, articleResponse: ArticleResponse){
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            db.articleDao().insertFavouriteArticle(articleResponse)
        }
        db.close()
    }
}