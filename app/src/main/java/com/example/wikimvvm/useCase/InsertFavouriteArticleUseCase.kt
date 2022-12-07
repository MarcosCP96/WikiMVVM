package com.example.wikimvvm.useCase

import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse

class InsertFavouriteArticleUseCase(private val articleDao: ArticleDAO) {
    fun insertFavouriteArticle(articleResponse: ArticleResponse){
        articleDao.insertFavouriteArticle(articleResponse)
    }
}