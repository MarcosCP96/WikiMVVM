package com.example.wikimvvm.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.wikimvvm.model.ArticleResponse

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM favouriteArticles")
    fun getAll(): List<ArticleResponse>

    @Query("SELECT * FROM favouriteArticles WHERE title LIKE :title")
    fun getArticle(title: String): Boolean

    @Query("DELETE FROM favouriteArticles")
    fun emptyFavouriteList()

    @Insert
    fun insertFavouriteArticle(favouriteArticle: ArticleResponse)

    @Delete
    fun deleteArticle(articleResponse: ArticleResponse)
}