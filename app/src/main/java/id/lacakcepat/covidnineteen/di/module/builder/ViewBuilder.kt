package id.lacakcepat.covidnineteen.di.module.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.lacakcepat.covidnineteen.ui.activity.*
import id.lacakcepat.covidnineteen.ui.fragment.HomeFragment
import id.lacakcepat.covidnineteen.ui.fragment.LoginFragment
import id.lacakcepat.covidnineteen.ui.fragment.RegisterFragment

@Module(includes = [ViewModelBuilder::class])
abstract class ViewBuilder {

    @ContributesAndroidInjector
    abstract fun bindOnBoardingActivity(): OnBoardingActivity

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindOtpVerificationActivity(): OtpVerificationActivity

    @ContributesAndroidInjector
    abstract fun bindAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindCovidCaseActivity(): CovidCaseActivity

}