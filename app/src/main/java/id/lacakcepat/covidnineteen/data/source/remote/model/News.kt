package id.lacakcepat.covidnineteen.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("urlToImage")
    var image: String? = null,

    @SerializedName("publishedAt")
    var publishedAt: String? = null

)