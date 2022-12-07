package com.example.wikimvvm.useCase;

import static org.mockito.Mockito.*;
import com.example.wikimvvm.daos.ArticleDAO;
import com.example.wikimvvm.model.ArticleResponse;
import com.example.wikimvvm.model.Thumbnail;

import org.junit.jupiter.api.Test;

class CheckIfArticleInFavouritesUseCaseTest{
    ArticleDAO articleDao = mock(ArticleDAO.class);
    @Test
    public void checkIfArticleInFavourite(){
        CheckIfArticleInFavouritesUseCase checkIfArticleInFavouritesUseCase = new CheckIfArticleInFavouritesUseCase(articleDao);
        Thumbnail thumbnail = new Thumbnail("");
        ArticleResponse articleResponse = new ArticleResponse("", thumbnail, "");
        checkIfArticleInFavouritesUseCase.checkIfArticleInFavourite(articleResponse);
        verify(articleDao).getArticle(articleResponse.getTitle());
    }
}