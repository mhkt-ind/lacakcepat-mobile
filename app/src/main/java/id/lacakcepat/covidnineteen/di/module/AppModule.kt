package id.lacakcepat.covidnineteen.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class,
        RepositoryModule::class,
        SharedPrefModule::class
    ]
)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context

}