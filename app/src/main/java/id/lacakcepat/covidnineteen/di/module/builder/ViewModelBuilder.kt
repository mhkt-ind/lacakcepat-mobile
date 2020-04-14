package id.lacakcepat.covidnineteen.di.module.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.lacakcepat.covidnineteen.di.factory.ViewModelFactory
import id.lacakcepat.covidnineteen.di.scope.ViewModelKey
import id.lacakcepat.covidnineteen.viewmodel.*

@Module
abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun registerViewModel(model: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(model: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(model: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CovidCaseViewModel::class)
    internal abstract fun covidCaseViewModel(model: CovidCaseViewModel): ViewModel

}