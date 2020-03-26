package id.lacakcepat.covidnineteen

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import id.lacakcepat.covidnineteen.di.component.AppComponent
import id.lacakcepat.covidnineteen.di.component.DaggerAppComponent
import javax.inject.Inject

class LacakCepatApp : Application(), HasAndroidInjector {

    companion object {
        lateinit var component: AppComponent
    }

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}