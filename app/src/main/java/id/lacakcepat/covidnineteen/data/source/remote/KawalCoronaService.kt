package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryCaseResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.GlobalDataCaseItem
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Module
interface KawalCoronaService {

    @GET("{country}")
    suspend fun getCountryDataCase(@Path("country") country: String?): Response<List<CountryCaseResponse>>

    @GET("{country}/{province}")
    suspend fun getProvinceDataCase(
        @Path("country") country: String,
        @Path("province") province: String
    ): List<ProvinceDataCase>

    @GET
    suspend fun getGlobalDataCase(): List<GlobalDataCaseItem>
}