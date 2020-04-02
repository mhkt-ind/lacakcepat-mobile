package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.remote.LacakCepatService
import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaRepository
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLacakCepatAppRepository(service: LacakCepatService): LacakCepatRepository = LacakCepatRepository(service)

    @Provides
    @Singleton
    fun provideKawalCoronaAppRepository(service: KawalCoronaService): KawalCoronaRepository = KawalCoronaRepository(service)
}