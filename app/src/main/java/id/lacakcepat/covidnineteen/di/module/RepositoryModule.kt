package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.data.source.remote.EndPointService
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatAppRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLacakCepatAppRepository(service: EndPointService): LacakCepatAppRepository = LacakCepatAppRepository(service)
}