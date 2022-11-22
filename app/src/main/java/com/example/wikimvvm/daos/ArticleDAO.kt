package com.example.wikimvvm.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wikimvvm.model.FavouriteArticle

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM favouriteArticles")
    fun getAll(): List<FavouriteArticle>

    @Insert
    fun insertFavouriteArticle(favouriteArticle: FavouriteArticle)
}