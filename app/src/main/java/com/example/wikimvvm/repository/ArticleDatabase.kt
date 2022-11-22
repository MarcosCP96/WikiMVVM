package com.example.wikimvvm.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wikimvvm.`interface`.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.ClassConverters
import com.example.wikimvvm.model.FavouriteArticle

@Database(entities = [FavouriteArticle::class], version = 1)
@TypeConverters(ClassConverters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
}