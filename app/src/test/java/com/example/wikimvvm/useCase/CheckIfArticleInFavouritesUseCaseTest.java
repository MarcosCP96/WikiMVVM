package com.example.wikimvvm.useCase;

import static org.mockito.Mockito.*;
import com.example.wikimvvm.daos.ArticleDAO;
import com.example.wikimvvm.model.ArticleResponse;
import com.example.wikimvvm.model.ContentURLs;
import com.example.wikimvvm.model.Mobile;
import com.example.wikimvvm.model.Thumbnail;

import org.junit.jupiter.api.Test;

class CheckIfArticleInFavouritesUseCaseTest{
    ArticleDAO articleDao = mock(ArticleDAO.class);
    @Test
    public void checkIfArticleInFavourite(){
        CheckIfArticleInFavouritesUseCase checkIfArticleInFavouritesUseCase = new CheckIfArticleInFavouritesUseCase(articleDao);
        Thumbnail thumbnail = new Thumbnail("");
        Mobile mobile = new Mobile("");
        ContentURLs contentURLs = new ContentURLs(mobile);
        ArticleResponse articleResponse = new ArticleResponse("", thumbnail, "", contentURLs);
        checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse);
        verify(articleDao).getArticle(articleResponse.getTitle());
    }
}