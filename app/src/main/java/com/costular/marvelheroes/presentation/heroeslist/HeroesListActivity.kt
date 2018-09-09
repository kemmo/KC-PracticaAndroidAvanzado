package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.MainApp
import com.costular.marvelheroes.presentation.util.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HeroesListActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var heroesListViewModel: HeroesListViewModel

    lateinit var adapter: HeroesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    override fun onResume() {
        super.onResume()
        heroesListViewModel.loadMarvelHeroes()
    }

    fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun setUp() {
        setUpRecycler()
        setUpViewModel()
    }

    private fun setUpRecycler() {
        adapter = HeroesListAdapter { hero, image -> goToHeroDetail(hero, image) }
        heroesListRecycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        heroesListRecycler.itemAnimator = DefaultItemAnimator()
        heroesListRecycler.adapter = adapter
    }

    private fun setUpViewModel() {
        heroesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(HeroesListViewModel::class.java)
        bindEvents()
    }

    private fun bindEvents(){
        heroesListViewModel.marvelListState.observe(this, Observer {heroList ->
            heroList?.let {
                onHeroesListLoaded(heroList)
            }
        })

        heroesListViewModel.isLoadingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })
    }

    private fun onHeroesListLoaded(marvelHeroesList: List<MarvelHeroEntity>) {
        adapter.swapData(marvelHeroesList)
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(this, hero, image)
    }

    fun showLoading(isLoading: Boolean) {
        heroesListLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}
