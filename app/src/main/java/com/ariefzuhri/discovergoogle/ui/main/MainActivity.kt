package com.ariefzuhri.discovergoogle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefzuhri.discovergoogle.common.action.openCustomTabs
import com.ariefzuhri.discovergoogle.common.util.gone
import com.ariefzuhri.discovergoogle.common.util.showToast
import com.ariefzuhri.discovergoogle.common.util.visible
import com.ariefzuhri.discovergoogle.common.view.adapter.ArticleAdapter
import com.ariefzuhri.discovergoogle.databinding.ActivityMainBinding
import com.ariefzuhri.discovergoogle.domain.model.Article
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initArticleAdapter()
        initArticleRecyclerView()
        populateArticleAdapter()
    }

    private fun initArticleAdapter() {
        articleAdapter = ArticleAdapter().apply {
            setEventListener(object : ArticleAdapter.EventListener {
                override fun onItemClick(article: Article) {
                    openCustomTabs(article.url)
                }
            })

            addLoadStateListener { loadState ->
                when (val currentState = loadState.refresh) {
                    is LoadState.Loading -> binding.pbArticle.visible()
                    is LoadState.Error -> showToast(currentState.error.message)
                    is LoadState.NotLoading -> binding.pbArticle.gone(true)
                }
            }
        }
    }

    private fun initArticleRecyclerView() {
        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = articleAdapter
        }
    }

    private fun populateArticleAdapter() {
        lifecycleScope.launch {
            viewModel.news.collectLatest { pagingData ->
                articleAdapter.submitData(pagingData)
            }
        }
    }
}