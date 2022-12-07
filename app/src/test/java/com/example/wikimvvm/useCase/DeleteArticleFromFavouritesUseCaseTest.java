package com.example.wikimvvm.useCase;

import static org.mockito.Mockito.*;
import com.example.wikimvvm.daos.ArticleDAO;
import com.example.wikimvvm.model.ArticleResponse;
import com.example.wikimvvm.model.Thumbnail;
import org.junit.jupiter.api.Test;

class DeleteArticleFromFavouritesUseCaseTest{
    ArticleDAO articleDao = mock(ArticleDAO.class);
    @Test
    public void deleteArticleFromFavourites(){
        DeleteArticleFromFavouritesUseCase deleteArticleFromFavouritesUseCase = new DeleteArticleFromFavouritesUseCase(articleDao);
        Thumbnail thumbnail = new Thumbnail("");
        ArticleResponse articleResponse = new ArticleResponse("", thumbnail, "");
        deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(articleResponse);
        verify(articleDao).deleteArticle(articleResponse);
    }
}