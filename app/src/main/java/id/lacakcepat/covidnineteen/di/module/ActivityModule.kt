package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.lacakcepat.covidnineteen.ui.activity.LoginActivity
import id.lacakcepat.covidnineteen.ui.activity.OnBoardingPageActivity
import id.lacakcepat.covidnineteen.ui.activity.SplashActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMatchActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindOnBoardingPageActivity(): OnBoardingPageActivity
}