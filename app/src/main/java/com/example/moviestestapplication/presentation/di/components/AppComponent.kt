package com.example.moviestestapplication.presentation.di.components

import com.example.moviestestapplication.app.TheApp
import com.example.moviestestapplication.presentation.di.modules.ActivitiesInjectionModule
import com.example.moviestestapplication.presentation.di.modules.ApplicationModule
import com.example.moviestestapplication.presentation.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            ViewModelModule::class,
            ApplicationModule::class,
            AndroidInjectionModule::class,
            ActivitiesInjectionModule::class
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: TheApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: TheApp)
}