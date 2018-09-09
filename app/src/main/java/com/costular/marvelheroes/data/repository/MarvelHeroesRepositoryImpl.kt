package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.SettingsManager
import io.reactivex.Flowable

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource,
                                 private val settingsManager: SettingsManager)
    : MarvelHeroesRepository {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            if (settingsManager.firstLoad)
                getMarvelHeroesFromRemoteService()
            else
                getMarvelHeroesFromLocalDatabase()

    private fun getMarvelHeroesFromLocalDatabase(): Flowable<List<MarvelHeroEntity>> = localMarvelHeroesDataSource.getMarvelHeroesList()

    private fun getMarvelHeroesFromRemoteService(): Flowable<List<MarvelHeroEntity>> =
            remoteMarvelHeroesDataSource.getMarvelHeroesList().doOnNext {
                localMarvelHeroesDataSource.saveMarvelHeroes(it)
            }

    override fun setMarvelHeroFavourite(marvelHeroEntity: MarvelHeroEntity): Flowable<MarvelHeroEntity> =
            Flowable.just(marvelHeroEntity)
                    .doOnNext {
                        localMarvelHeroesDataSource.setMarvelHeroFavourite(it)
                    }

}