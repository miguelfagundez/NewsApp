package com.devproject.miguelfagundez.newsapp.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.devproject.miguelfagundez.newsapp.R
import com.devproject.miguelfagundez.newsapp.util.Config
import kotlinx.android.synthetic.main.website_fragment_layout.*
import java.util.zip.Inflater

class NewsDetailsFragment :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.website_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(Config.KEY_VALUE_BUNDLE)?.let { url ->
            webview_news_url.loadUrl(url)
        }


    }
}