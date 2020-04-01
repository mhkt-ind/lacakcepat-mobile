package id.lacakcepat.covidnineteen.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.lacakcepat.covidnineteen.di.factory.ViewModelFactory
import id.lacakcepat.covidnineteen.di.scope.ViewModelKey
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    protected abstract fun loginViewModel(model: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CovidCaseViewModel::class)
    protected abstract fun covidCaseViewModel(model: CovidCaseViewModel): ViewModel
}