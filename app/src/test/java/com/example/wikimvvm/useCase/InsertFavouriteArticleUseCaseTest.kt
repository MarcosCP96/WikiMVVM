package com.example.wikimvvm.useCase

import com.example.wikimvvm.daos.ArticleDAO
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.ContentURLs
import com.example.wikimvvm.model.Mobile
import com.example.wikimvvm.model.Thumbnail
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class InsertFavouriteArticleUseCaseTest {
    @Test
    fun insertFavouriteArticle() {
        val daoMock: ArticleDAO = mock()
        val insertFavouriteArticleUseCase = InsertFavouriteArticleUseCase(daoMock)
        val articleResponse = ArticleResponse("", Thumbnail(""), "", ContentURLs(Mobile("")))
        insertFavouriteArticleUseCase.insertFavouriteArticle(articleResponse)
        verify(daoMock).insertFavouriteArticle(articleResponse)
    }
}