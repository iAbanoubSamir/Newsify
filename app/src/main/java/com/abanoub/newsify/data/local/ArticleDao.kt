package com.abanoub.newsify.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("DELETE FROM articles WHERE url == :url")
    suspend fun delete(url: String)

    @Query("SELECT * FROM articles")
    fun getArticle(): List<ArticleEntity>
}