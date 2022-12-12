package com.example.wikimvvm.model

import androidx.room.TypeConverter
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import retrofit2.http.Url
import java.net.URL

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
    fun fromContentToMobile(contentURLs: ContentURLs): Mobile {
        return contentURLs.mobile
    }

    @TypeConverter
    fun fromMobileToContent(mobile: Mobile): ContentURLs {
        return ContentURLs(mobile)
    }

    @TypeConverter
    fun fromMobileToList(mobile: Mobile): List<String>{
        return mobile.page
    }

    @TypeConverter
    fun fromListToMobile(list: List<String>): Mobile{
        return Mobile(list)
    }

    @TypeConverter
    fun fromContentToString(contentURLs: ContentURLs): String{
        return contentURLs.mobile.page[0]
    }

    @TypeConverter
    fun fromStringToContent(string: String): ContentURLs{
        return ContentURLs(Mobile(listOf(string)))
    }
}