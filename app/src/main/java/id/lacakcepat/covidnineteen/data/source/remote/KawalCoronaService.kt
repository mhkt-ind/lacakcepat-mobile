package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryDataCase
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.GlobalDataCase
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

@Module
interface KawalCoronaService {

    @GET("{country}")
    fun getCountryDataCase(@Path("country") country: String?): Call<CountryDataCase?>

    @GET("{country}/{province}")
    fun getProvinceDataCase(
        @Path("country") country: String?,
        @Path("province") province: String?
    ): Call<List<ProvinceDataCase?>>

    @GET("")
    fun getGlobalDataCase(): Call<GlobalDataCase?>

}