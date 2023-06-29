package com.abanoub.newsify.data.mapper

import com.abanoub.newsify.data.local.ArticleEntity
import com.abanoub.newsify.domain.model.Article

class ArticleMapperImpl : ArticleMapper {

    override fun fromEntity(entity: ArticleEntity): Article {
        return Article(
            source = entity.source,
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            content = entity.content
        )
    }

    override fun toEntity(article: Article): ArticleEntity {
        return ArticleEntity(
            source = article.source,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt,
            content = article.content
        )
    }

}