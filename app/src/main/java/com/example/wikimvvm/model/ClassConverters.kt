package com.example.wikimvvm.model

import androidx.room.TypeConverter
import retrofit2.http.Url
import java.net.URL

class ClassConverters {
    @TypeConverter
    fun fromThumbnailToString(thumbnail: Thumbnail?): String?{
        return thumbnail?.source
    }

    @TypeConverter
    fun fromStringToThumbnail(string: String?): Thumbnail?{
        if (string == null){
            return null
        }
        return Thumbnail(string)
    }

//    @TypeConverter
//    fun fromContentToList(list: List<List<String>>): String {
//        return list[0][0]
//    }
//
//    @TypeConverter
//    fun fromListToContent(url: String): List<List<String>>{
//        return listOf(listOf(url))
//    }
}