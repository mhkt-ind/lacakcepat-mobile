package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.NewsService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.news.NewsResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(@Named("News") private val service: NewsService) {

    suspend fun getNewsHeadlines(country: String?): Result<NewsResponse> =
        try {
            Result.Success(service.getNewsHeadlines(country).body())
        } catch (e: Throwable) {
            Result.Error(e)
        }

}