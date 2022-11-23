package com.example.wikimvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteArticles")
data class ArticleResponse(@PrimaryKey var title: String,
                           var thumbnail: Thumbnail,
                           var extract: String) : java.io.Serializable