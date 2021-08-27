package com.example.stateflow.daggerbuilder.component

import com.example.stateflow.daggerbuilder.module.ApiModule
import com.example.stateflow.repository.Repository
import com.example.stateflow.ui.mainactivity.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {

    fun inject( repository: Repository)

    fun inject( mainViewModel : MainViewModel )

}