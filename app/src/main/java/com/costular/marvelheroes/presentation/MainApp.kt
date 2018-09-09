package com.costular.marvelheroes.presentation

import android.app.Application
import com.costular.marvelheroes.di.components.ApplicationComponent
import com.costular.marvelheroes.di.components.DaggerApplicationComponent
import com.costular.marvelheroes.di.modules.ApplicationModule

/**
 * Created by costular on 16/03/2018.
 */
class MainApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component =
                DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()

    }
}