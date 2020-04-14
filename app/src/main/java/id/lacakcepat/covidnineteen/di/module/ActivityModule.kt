package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.lacakcepat.covidnineteen.ui.activity.*

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMatchActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindOnBoardingPageActivity(): OnBoardingActivity

    @ContributesAndroidInjector
    abstract fun bindCovidCaseActivity(): CovidCaseActivity

    @ContributesAndroidInjector
    abstract fun bindNewsActivity(): NewsActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}