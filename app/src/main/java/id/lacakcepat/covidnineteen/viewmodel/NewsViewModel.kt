package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.lacakcepat.covidnineteen.data.source.remote.model.response.newsapi.NewsResponse.Article
import id.lacakcepat.covidnineteen.data.source.repository.NewsAPIRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsAPIRepository) :
    ViewModel() {

    var recentNews: MutableLiveData<Result<List<Article>>> = MutableLiveData()
    var trendNews: MutableLiveData<Result<List<Article>>> = MutableLiveData()
    var otherNews: MutableLiveData<Result<List<Article>>> = MutableLiveData()

    fun getRecentNews() {
        viewModelScope.launch {
            val newsResource = async { repository.getLatestNews() }
            recentNews.postValue(newsResource.await())
        }
    }

    fun getHeadlineNews() {
        viewModelScope.launch {
            val newsResource = async { repository.getHeadlineNews() }
            trendNews.postValue(newsResource.await())
        }
    }

    fun getOtherNews() {
        viewModelScope.launch {
            val newsResource = async { repository.getOtherNews(2) }
            otherNews.postValue(newsResource.await())
        }
    }

}