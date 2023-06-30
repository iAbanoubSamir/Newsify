package com.abanoub.newsify.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abanoub.newsify.databinding.ArticleItemBinding
import com.abanoub.newsify.domain.model.Article
import com.bumptech.glide.Glide

class ArticlesAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Article, ArticlesAdapter.ArticlesViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val articleItemBinding =
            ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(articleItemBinding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


    inner class ArticlesViewHolder(private val binding: ArticleItemBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = getItem(position)
                    listener.onArticleClick(article)
                }
            }
        }

        fun bind(article: Article) {
            binding.apply {
                Glide.with(binding.root).load(article.urlToImage).into(ivArticleImage)
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvDatePublished.text = article.publishedAt
                tvSource.text = article.source?.name
            }
        }
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onArticleClick(article: Article)
    }

}