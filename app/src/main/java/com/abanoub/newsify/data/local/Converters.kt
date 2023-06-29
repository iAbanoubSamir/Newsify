package com.abanoub.newsify.data.local

import androidx.room.TypeConverter
import com.abanoub.newsify.domain.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name.toString()
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}