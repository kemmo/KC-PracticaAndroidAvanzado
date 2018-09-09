package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.database.MarvelHeroesDatabase
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class LocalMarvelHeroesDataSource(val marvelHeroDatabase: MarvelHeroesDatabase) : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> = marvelHeroDatabase.getMarvelHeroesDao().getAllMarvelHeroes().toFlowable()

    fun saveMarvelHeroes(marvelHeroes: List<MarvelHeroEntity>){
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroesDao().removeAndInsertMarvelHeroes(marvelHeroes)
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }
}
