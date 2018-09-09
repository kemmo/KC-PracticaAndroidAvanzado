package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepository
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarvelHeroDetailViewModel @Inject constructor(private val marvelHeroesRepository: MarvelHeroesRepository)
    : BaseViewModel() {

    val marvelHeroDetailState: MutableLiveData<MarvelHeroEntity> = MutableLiveData()

    fun setMarvelHeroFavourite(marvelHeroEntity: MarvelHeroEntity){
        marvelHeroesRepository.setMarvelHeroFavourite(marvelHeroEntity)?.let {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        marvelHeroDetailState.value = marvelHeroEntity
                    }
                    .addTo(compositeDisposable)
        }

    }
}
