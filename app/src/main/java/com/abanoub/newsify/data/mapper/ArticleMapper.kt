package com.abanoub.newsify.data.mapper

import com.abanoub.newsify.data.local.ArticleEntity
import com.abanoub.newsify.domain.model.Article

interface ArticleMapper {

    fun fromEntity(entity: ArticleEntity): Article

    fun toEntity(article: Article): ArticleEntity
}