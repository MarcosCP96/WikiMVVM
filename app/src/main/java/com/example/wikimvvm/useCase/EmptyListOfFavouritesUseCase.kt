package com.example.wikimvvm.useCase

import android.content.Context
import androidx.room.Room
import com.example.wikimvvm.repository.ArticleDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmptyListOfFavouritesUseCase {
    fun emptyListOfFavourites(context: Context){
        val db = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "articlesDB"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            db.articleDao().emptyFavouriteList()
        }
        db.close()
    }
}