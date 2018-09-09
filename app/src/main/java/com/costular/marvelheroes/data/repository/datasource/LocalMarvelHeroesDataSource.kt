package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

class LocalMarvelHeroesDataSource : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
        return Flowable.just(
                //TODO: Get from local database
                listOf()
        )
    }
}
