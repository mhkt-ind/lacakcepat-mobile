package id.lacakcepat.covidnineteen.data.source.remote.model.response.news

import com.google.gson.annotations.SerializedName
import id.lacakcepat.covidnineteen.data.source.remote.model.News

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<News>
)