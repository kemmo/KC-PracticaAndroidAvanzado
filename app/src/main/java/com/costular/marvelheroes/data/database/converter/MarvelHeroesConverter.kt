package com.costular.marvelheroes.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MarvelHeroesConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): Array<String>? {
        return if (value == null) null else {
            val listType = object : TypeToken<Array<String>>() {
            }.type
            return Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: Array<String>?): String {
        return if (list == null) "" else Gson().toJson(list)
    }
}