package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.lacakcepat.covidnineteen.ui.fragment.LoginFragment
import id.lacakcepat.covidnineteen.ui.fragment.LoginVerificationFragment
import id.lacakcepat.covidnineteen.ui.fragment.RegisterFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindLoginVerificationFragment(): LoginVerificationFragment
}