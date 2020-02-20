package com.devproject.miguelfagundez.newsapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.devproject.miguelfagundez.newsapp.R
import com.devproject.miguelfagundez.newsapp.adapter.NewsAdapter
import com.devproject.miguelfagundez.newsapp.model.Article
import com.devproject.miguelfagundez.newsapp.model.NewsObject
import com.devproject.miguelfagundez.newsapp.util.Config
import com.devproject.miguelfagundez.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

/****************************************
 * MainActivity class.
 * First activity to lunch
 * @author Miguel Fagundez
 * @since 02/20/2020
 * @version 1.0
 **************************************/
class MainActivity : AppCompatActivity() {

    // Members
    private lateinit var viewModel : NewsViewModel
    private lateinit var observer : Observer<List<Article>>

    // Fragment
    private val website_fragment = NewsDetailsFragment()

    /*****************************************************************
     * Catching broadcast receiver (passing url)
     * *****************************************************************/
    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            // the broadcast sent the url and we catch it
            // open news website in a fragment
            intent?.getStringExtra(Config.KEY_WEBSITE_VALUE)?.let {url ->
                openWebsite(url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Registering the broadcast
        registerReceiver(receiver, IntentFilter(Config.KEY_WEBSITE_FILTER_ADAPTER))

        // Defining the ViewModel component with the MovieViewModel class
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        // Defining the observer object with the recycler view data
        observer = Observer {newsObject ->

            val adapter = NewsAdapter(newsObject)
            news_recycler_view.layoutManager = LinearLayoutManager(this)
            news_recycler_view.adapter = adapter

        }

        viewModel.getTopHeadlines("us").observe(this, observer)

    }

    /*****************************************************************
     * openWebsite Method
     *  include website url into a bundle
     *  fragment takes the url into its arguments
     *  begin fragment transaction
     * After a click into the recycler view, the url is passed
     * and a fragment object is create to show its details
     * *****************************************************************/
    private fun openWebsite(url: String) {
        Toast.makeText(this, "url: $url", Toast.LENGTH_SHORT).show()
        url?.let { url ->
            val bundle = Bundle()
            bundle.putString(Config.KEY_VALUE_BUNDLE, url)

            website_fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.fragment_in_slide,
                    R.anim.fragment_out_slide,
                    R.anim.fragment_in_slide,
                    R.anim.fragment_out_slide)
                .add(R.id.news_fragment_container, website_fragment)
                .addToBackStack(website_fragment.tag)
                .commit()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // broadcast must be unregistered
        unregisterReceiver(receiver)
    }

}
