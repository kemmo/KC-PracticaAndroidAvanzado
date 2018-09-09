package com.costular.marvelheroes.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.costular.marvelheroes.data.database.converter.MarvelHeroesConverter
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

@Database(entities = [MarvelHeroEntity::class], version = 1)
@TypeConverters(MarvelHeroesConverter::class)
abstract class MarvelHeroesDatabase: RoomDatabase() {
    abstract fun getMarvelHeroesDao(): MarvelHeroesDao
}
