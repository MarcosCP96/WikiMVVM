package com.example.wikimvvm.useCase

import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse

class CheckIfArticleInFavouritesUseCase(private val articleDao: ArticleDAO) {
    fun checkIfArticleInFavourite(articleResponse: ArticleResponse): Boolean =
        articleDao.getArticle(articleResponse.title) == null
}