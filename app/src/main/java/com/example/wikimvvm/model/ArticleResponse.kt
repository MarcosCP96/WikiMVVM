package com.example.wikimvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

//@Serializable
//data class ArticleResponse(var title: String,
//                           var thumbnail: Thumbnail,
//                           var extract: String) : java.io.Serializable
data class ArticleResponse(var title: String,
                           var thumbnail: Thumbnail,
                           var extract: String) : java.io.Serializable