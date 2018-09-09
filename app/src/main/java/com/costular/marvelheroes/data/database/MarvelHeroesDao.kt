package com.costular.marvelheroes.data.database

import android.arch.persistence.room.*
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Maybe

@Dao
abstract class MarvelHeroesDao {

    @Query("SELECT * FROM marvelHeroes")
    abstract fun getAllMarvelHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAll(marvelHeroes: List<MarvelHeroEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateMarvelHero(marvelHero: MarvelHeroEntity):Int

    @Query("DELETE FROM marvelHeroes")
    abstract fun deleteAllMarvelHeroes()

    @Transaction
    open fun removeAndInsertMarvelHeroes(marvelHeroes: List<MarvelHeroEntity>) {
        deleteAllMarvelHeroes()
        insertAll(marvelHeroes)
    }

}
