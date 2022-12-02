package com.example.wikimvvm.useCase

import android.content.Context
import android.widget.Toast
import com.example.wikimvvm.model.ArticleResponse

class ArticleDeletedUseCase {
    fun showToast(context: Context, articleResponse: ArticleResponse){
        Toast.makeText(context, "${articleResponse.title} eliminado de favoritos", Toast.LENGTH_SHORT).show()
    }
}