package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryDataCase
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.GlobalDataCase
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import id.lacakcepat.covidnineteen.utilities.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class KawalCoronaAppRepository {

    private val retrofit: Retrofit? = RetrofitClientInstance.retrofitInstance
    private val service = retrofit?.create(KawalCoronaService::class.java)

    fun getCountry(country: String): Call<CountryDataCase?>? {
        return service?.getCountryDataCase(country)
    }

    fun getProvince(country: String,province: String): Call<List<ProvinceDataCase?>>? {
        return service?.getProvinceDataCase(country,province)
    }

    fun getGlobal(): Call<GlobalDataCase?>? {
        return service?.getGlobalDataCase()
    }

}