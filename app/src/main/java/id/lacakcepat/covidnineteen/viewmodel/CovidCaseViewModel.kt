package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryDataCaseItem
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase

import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaAppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class CovidCaseViewModel @Inject constructor(private val repository: KawalCoronaRepository) :
    ViewModel() {

    var countryCase: MutableLiveData<Result<List<CountryDataCaseItem?>>> = MutableLiveData()
    var provinceCase: MutableLiveData<Result<List<ProvinceDataCase?>>> = MutableLiveData()

    private val country = "indonesia"
    private val province = "provinsi"

    fun getCountry() {
        repository.getCountry(country)?.enqueue(object : Callback<CountryDataCase?> {
            override fun onFailure(call: Call<CountryDataCase?>, t: Throwable) {
                println("Fail Country")
                println(t)
            }

            override fun onResponse(call: Call<CountryDataCase?>, response: Response<CountryDataCase?>) {
                val data = response.body()
                countryCase.postValue(data?.get(0))
            }

        })
    }

    fun getProvince() {
        repository.getProvince(country, province)?.enqueue(object : Callback<List<ProvinceDataCase?>> {
            override fun onFailure(call: Call<List<ProvinceDataCase?>>, t: Throwable) {
                println("Fail Province")
            }

            override fun onResponse(
                call: Call<List<ProvinceDataCase?>>,
                response: Response<List<ProvinceDataCase?>>
            ) {
                val data = response.body()
                provinceCase.postValue(data)
            }
        })
    }

    fun getFilterProvince(query:String?):List<ProvinceDataCase?>?{
        return provinceCase.value?.filter {
            it?.provinceDataItem?.provinsi!!.toLowerCase(Locale.getDefault()).contains(query!!)
        }
    }
}