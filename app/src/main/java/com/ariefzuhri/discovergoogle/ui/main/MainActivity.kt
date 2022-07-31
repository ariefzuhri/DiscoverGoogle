package com.ariefzuhri.discovergoogle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefzuhri.discovergoogle.common.action.openCustomTabs
import com.ariefzuhri.discovergoogle.common.view.adapter.ArticleAdapter
import com.ariefzuhri.discovergoogle.databinding.ActivityMainBinding
import com.ariefzuhri.discovergoogle.domain.model.Article
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private val articleAdapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initArticleRecyclerView()
        populateArticleAdapter()
    }

    private fun initArticleRecyclerView() {
        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = articleAdapter
        }

        articleAdapter.setEventListener(object : ArticleAdapter.EventListener {
            override fun onArticleClicked(article: Article) {
                openCustomTabs(article.url)
            }
        })
    }

    private fun populateArticleAdapter() {
        lifecycleScope.launch {
            viewModel.news.collectLatest { pagingData ->
                articleAdapter.submitData(pagingData)
            }
        }
    }
}