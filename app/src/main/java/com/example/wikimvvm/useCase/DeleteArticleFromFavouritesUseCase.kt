package com.example.wikimvvm.useCase

import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse

class DeleteArticleFromFavouritesUseCase(private val articleDAO: ArticleDAO) {
    fun deleteArticleFromFavourites(articleToDelete: ArticleResponse) {
        articleDAO.deleteArticle(articleToDelete)
    }
}