package com.example.wikimvvm.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.wikimvvm.R
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.ContentURLs
import com.example.wikimvvm.model.Mobile
import com.example.wikimvvm.model.Thumbnail

class WebFragment : Fragment() {
    private var articleSent = ArticleResponse("", Thumbnail(""), "", ContentURLs(Mobile("")))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web, container, false)
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable("articulo").let {
            articleSent = it as ArticleResponse
        }
        val webview = view.findViewById<WebView>(R.id.webview)
        webview.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        webview.loadUrl(articleSent.content_urls.mobile.page)
        webview.settings.javaScriptEnabled = true
        webview.settings.allowContentAccess = true
        webview.settings.domStorageEnabled = true
        webview.settings.useWideViewPort = true
    }
}