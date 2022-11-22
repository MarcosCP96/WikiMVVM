package com.example.wikimvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class FavouriteArticle(@PrimaryKey val title: String,
                            val thumbnail: Thumbnail,
                            val extract: String)