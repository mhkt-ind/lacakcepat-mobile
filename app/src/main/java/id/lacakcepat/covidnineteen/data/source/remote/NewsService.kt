package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.data.source.remote.model.response.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Module
interface NewsService {

    @GET("top-headlines")
    suspend fun getNewsHeadlines(@Query("country") country: String?): Response<NewsResponse>

}