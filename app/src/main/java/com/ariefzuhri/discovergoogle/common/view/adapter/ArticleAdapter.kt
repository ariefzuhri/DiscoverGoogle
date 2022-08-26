package com.ariefzuhri.discovergoogle.common.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ariefzuhri.discovergoogle.common.util.gone
import com.ariefzuhri.discovergoogle.common.util.load
import com.ariefzuhri.discovergoogle.databinding.ItemArticleBinding
import com.ariefzuhri.discovergoogle.domain.model.Article

class ArticleAdapter :
    PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleComparator) {

    private var listener: EventListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        article?.let { holder.bind(it) }
    }

    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(article: Article) {
            binding.apply {
                initView(article)
                initClickListeners(article)
            }
        }

        private fun ItemArticleBinding.initView(article: Article) {
            imgArticle.load(article.imageUrl)
            imgArticle.contentDescription = article.title

            tvTitle.text = article.title

            tvAuthor.text = article.author
            tvAuthor.gone(article.author.isEmpty())

            tvPublishedTime.text = article.getFuzzyPublishedTime(context)
        }

        private fun ItemArticleBinding.initClickListeners(article: Article) {
            root.setOnClickListener {
                listener?.onItemClick(article)
            }
        }
    }

    fun setEventListener(listener: EventListener?) {
        this.listener = listener
    }

    interface EventListener {

        fun onItemClick(article: Article)
    }
}