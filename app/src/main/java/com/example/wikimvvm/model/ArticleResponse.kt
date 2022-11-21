package com.example.wikimvvm.model

import kotlinx.serialization.Serializable

//@Serializable
data class ArticleResponse(var title: String,
                           var thumbnail: Thumbnail,
                           var extract: String) : java.io.Serializable