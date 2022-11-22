package com.example.wikimvvm.model

import androidx.room.TypeConverter

class ClassConverters {
    @TypeConverter
    fun fromThumbnailToString(thumbnail: Thumbnail): String{
        return thumbnail.source
    }

    @TypeConverter
    fun fromStringToThumbnail(string: String): Thumbnail{
        return Thumbnail(string)
    }
}