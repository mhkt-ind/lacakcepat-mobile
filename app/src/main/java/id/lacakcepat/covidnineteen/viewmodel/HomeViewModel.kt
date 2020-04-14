package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryCaseResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.news.NewsResponse
import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaRepository
import id.lacakcepat.covidnineteen.data.source.repository.NewsRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val kawalCoronaRepository: KawalCoronaRepository,
    private val newsRepository: NewsRepository
) :
    ViewModel() {

    private var _covidCase: MutableLiveData<CountryCaseResponse>? = null
    fun getCovidCase(country: String?): LiveData<CountryCaseResponse>? {

        if (_covidCase == null) {
            _covidCase = MutableLiveData()

            GlobalScope.launch {

                when (val response = kawalCoronaRepository.getCountryCase(country)) {

                    is Result.Success -> _covidCase?.postValue(response.data?.get(0))

                    is Result.Error -> response.exception?.printStackTrace()

                }

            }

        }

        return _covidCase
    }

    private var _news: MutableLiveData<NewsResponse>? = null
    fun getNews(country: String?): LiveData<NewsResponse>? {

        if (_news == null) {
            _news = MutableLiveData()

            GlobalScope.launch {

                when (val response = newsRepository.getNewsHeadlines(country)) {

                    is Result.Success -> _news?.postValue(response.data)

                    is Result.Error -> response.exception?.printStackTrace()

                }

            }

        }

        return _news
    }

}