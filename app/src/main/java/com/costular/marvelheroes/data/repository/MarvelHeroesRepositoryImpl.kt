package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource,
                                 private val marvelHeroesMapper: MarvelHeroMapper)
    : MarvelHeroesRepository {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            getMarvelHeroesFromRemoteService()
                    //TODO: .concatWith(getMarvelHeroesFromLocalDatabase() )

    private fun getMarvelHeroesFromLocalDatabase(): Flowable<List<MarvelHeroEntity>> = localMarvelHeroesDataSource.getMarvelHeroesList()

    private fun getMarvelHeroesFromRemoteService(): Flowable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList().doOnNext {
                //TODO: Save marvel hero list in local database
            }

}