package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryCaseResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import javax.inject.Inject

class CovidCaseViewModel @Inject constructor(private val repository: KawalCoronaRepository) :
    ViewModel() {

    var countryCase: MutableLiveData<Result<List<CountryCaseResponse?>>> = MutableLiveData()
    var provinceCase: MutableLiveData<Result<List<ProvinceDataCase?>>> = MutableLiveData()

    private val country = "indonesia"
    private val province = "provinsi"

    fun getCountry() {
//        viewModelScope.launch {
//            val countryResource = async { repository.getCountry(country) }
//            countryCase.postValue(countryResource.await())
//        }
    }

    fun getProvince() {
//        viewModelScope.launch {
//            val proviceResource = async { repository.getProvince(country, province) }
//            provinceCase.postValue(proviceResource.await())
//        }
    }

//    fun getFilterProvince(query:String?):List<ProvinceDataCase?>?{
//        return provinceCase.value?.filter {
//            it?.provinceDataItem?.provinsi!!.toLowerCase(Locale.getDefault()).contains(query!!)
//        }
//    }

}