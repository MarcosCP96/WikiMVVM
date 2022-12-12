package com.example.wikimvvm.model

import androidx.room.TypeConverter

class ClassConverters {

    @TypeConverter
    fun fromThumbnailToString(thumbnail: Thumbnail?): String? {
        return thumbnail?.source
    }

    @TypeConverter
    fun fromStringToThumbnail(string: String?): Thumbnail? {
        if (string == null) {
            return null
        }
        return Thumbnail(string)
    }

    @TypeConverter
    fun fromContentToString(contentURLs: ContentURLs): String{
        return contentURLs.mobile.page
    }

    @TypeConverter
    fun fromStringToContent(string: String): ContentURLs{
        return ContentURLs(Mobile(string))
    }
}