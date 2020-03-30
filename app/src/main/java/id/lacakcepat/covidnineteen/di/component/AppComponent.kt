package id.lacakcepat.covidnineteen.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import id.lacakcepat.covidnineteen.LacakCepatApp
import id.lacakcepat.covidnineteen.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ApiModule::class,
    RepositoryModule::class,
    SharedPrefModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class
])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: LacakCepatApp)
}