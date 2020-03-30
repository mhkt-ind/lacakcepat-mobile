package id.lacakcepat.covidnineteen.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("lacakcepat", Context.MODE_PRIVATE)

    @Binds
    @Singleton
    abstract fun provideSharedPreference(sharedPref: SharedPreferences): SharedPreference
}