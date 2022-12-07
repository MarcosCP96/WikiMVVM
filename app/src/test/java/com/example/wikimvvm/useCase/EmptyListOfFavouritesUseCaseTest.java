package com.example.wikimvvm.useCase;

import static org.mockito.Mockito.*;
import com.example.wikimvvm.daos.ArticleDAO;
import com.example.wikimvvm.model.ArticleResponse;
import com.example.wikimvvm.model.Thumbnail;
import org.junit.jupiter.api.Test;

class EmptyListOfFavouritesUseCaseTest{
    ArticleDAO articleDao = mock(ArticleDAO.class);
    @Test
    public void deleteArticleFromFavourites(){
        EmptyListOfFavouritesUseCase emptyListOfFavouritesUseCase = new EmptyListOfFavouritesUseCase(articleDao);
        emptyListOfFavouritesUseCase.emptyListOfFavourites();
        verify(articleDao).emptyFavouriteList();
    }
}