package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.BuildConfig
import id.lacakcepat.covidnineteen.data.source.remote.model.response.newsapi.NewsResponse.Article
import retrofit2.http.GET
import retrofit2.http.Query

@Module
interface NewsAPIService {

    //https://newsapi.org/v2/top-headlines?country=id&apiKey=8de9d283c87e46babb122afce0212a56&pageSize=10&category=health
    @GET("top-headlines?country=id&pageSize=10&category=health&apiKey=" + BuildConfig.NEWS_API_KEY)
    suspend fun getHeadlineNews(): List<Article>

    //https://newsapi.org/v2/everything?q=Corona&apiKey=8de9d283c87e46babb122afce0212a56&language=id&pageSize=10
    @GET("everything?q=Corona&language=id&pageSize=10&apiKey=" + BuildConfig.NEWS_API_KEY)
    suspend fun getLatestNews(): List<Article>

    @GET("everything?q=Corona&language=id&pageSize=10")
    suspend fun getOtherNews(
        @Query("page") page: Int
    ): List<Article>

}