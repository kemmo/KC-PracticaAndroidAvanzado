package com.costular.marvelheroes.domain.usecase

import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by costular on 17/03/2018.
 */
class GetMarvelHeroesList @Inject constructor(val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl)
    : UseCase<List<MarvelHeroEntity>>() {

    override fun buildCase(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroesRepositoryImpl.getMarvelHeroesList()

}