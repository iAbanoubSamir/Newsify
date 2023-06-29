package com.abanoub.newsify.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abanoub.newsify.domain.model.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var source: Source? = Source(),
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)
