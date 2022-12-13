package com.example.wikimvvm.useCase;

import static org.mockito.Mockito.*;
import com.example.wikimvvm.daos.ArticleDAO;
import com.example.wikimvvm.model.ArticleResponse;
import com.example.wikimvvm.model.ContentURLs;
import com.example.wikimvvm.model.Mobile;
import com.example.wikimvvm.model.Thumbnail;
import org.junit.jupiter.api.Test;

class DeleteArticleFromFavouritesUseCaseTest{
    ArticleDAO articleDao = mock(ArticleDAO.class);
    @Test
    public void deleteArticleFromFavourites(){
        DeleteArticleFromFavouritesUseCase deleteArticleFromFavouritesUseCase = new DeleteArticleFromFavouritesUseCase(articleDao);
        Thumbnail thumbnail = new Thumbnail("");
        Mobile mobile = new Mobile("");
        ContentURLs contentURLs = new ContentURLs(mobile);
        ArticleResponse articleResponse = new ArticleResponse("", thumbnail, "", contentURLs);
        deleteArticleFromFavouritesUseCase.deleteArticleFromFavourites(articleResponse);
        verify(articleDao).deleteArticle(articleResponse);
    }
}