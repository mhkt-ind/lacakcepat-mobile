package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.NewsAPIService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.newsapi.NewsResponse.Article
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class NewsAPIRepository @Inject constructor(@Named("NewsAPI") private val service: NewsAPIService) {

    suspend fun getHeadlineNews(): Result<List<Article>> {
        return try {
            Result.Success(
                service.getHeadlineNews().articles
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getLatestNews(): Result<List<Article>> {
        return try {
            Result.Success(
                service.getLatestNews().articles
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getOtherNews(page:Int): Result<List<Article>> {
        return try {
            Result.Success(
                service.getOtherNews(page).articles
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}