package com.example.wikimvvm.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.ClassConverters

@Database(entities = [ArticleResponse::class], version = 3)
@TypeConverters(ClassConverters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
}