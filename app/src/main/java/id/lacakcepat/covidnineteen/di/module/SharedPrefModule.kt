package id.lacakcepat.covidnineteen.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.util.SharedPreference
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("lacakcepat", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPreference(sharedPref: SharedPreferences): SharedPreference = SharedPreference(sharedPref)

}
