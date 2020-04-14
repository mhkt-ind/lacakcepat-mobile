package id.lacakcepat.covidnineteen.di

import dagger.android.DaggerApplication
import id.lacakcepat.covidnineteen.di.component.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector() =
        DaggerAppComponent.builder().application(this).build()

}