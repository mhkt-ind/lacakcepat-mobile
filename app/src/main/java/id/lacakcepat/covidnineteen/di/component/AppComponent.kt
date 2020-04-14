package id.lacakcepat.covidnineteen.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.lacakcepat.covidnineteen.di.App
import id.lacakcepat.covidnineteen.di.module.*
import id.lacakcepat.covidnineteen.di.module.builder.ViewBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent

    }

}