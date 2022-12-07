package com.example.wikimvvm.useCase

import com.example.wikimvvm.daos.ArticleDAO

class EmptyListOfFavouritesUseCase(private val articleDAO: ArticleDAO) {
    fun emptyListOfFavourites() {
        articleDAO.emptyFavouriteList()
    }
}