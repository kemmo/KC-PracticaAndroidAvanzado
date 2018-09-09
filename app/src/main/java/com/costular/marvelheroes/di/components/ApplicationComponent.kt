package com.costular.marvelheroes.di.components

import com.costular.marvelheroes.di.modules.ApplicationModule
import com.costular.marvelheroes.di.modules.DataModule
import com.costular.marvelheroes.di.modules.NetModule
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroeDetailActivity
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import com.costular.marvelheroes.presentation.util.mvvm.ViewModelModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by costular on 16/03/2018.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(marvelListActivity: HeroesListActivity)
    fun inject(marvelHeroDetailActivity: MarvelHeroeDetailActivity)
}