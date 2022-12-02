package com.example.wikimvvm.useCase

import android.content.Context
import android.widget.Toast
import com.example.wikimvvm.model.ArticleResponse

class ArticleInDbToastUseCase {
    fun showToast(context: Context, articleResponse: ArticleResponse){
        Toast.makeText(context, "${articleResponse.title} ya está en favoritos", Toast.LENGTH_SHORT).show()
    }
}