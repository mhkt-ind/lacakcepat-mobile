package id.lacakcepat.covidnineteen.data.source.remote.model.response.newsapi


import com.google.gson.annotations.SerializedName

//Link Example
//https://newsapi.org/v2/top-headlines?country=id&apiKey=8de9d283c87e46babb122afce0212a56&pageSize=10&category=health
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String, // ok
    @SerializedName("totalResults")
    val totalResults: Int // 70
) {
    data class Article(
        @SerializedName("author")
        val author: String, // Redaksi GaluhID
        @SerializedName("content")
        val content: String, // Berita Banjar, galuh.id – Pasca ditemukannya 1 orang positif terinfeksi virus Corona (Covid-19) awal April 2020 lalu, suasana pusat kota Banjar tidak seramai hari-hari sebelumnya.Apalagi saat ini, pasien positif Corona di Kota Banjar bertambah menjadi 2 ora… [+1586 chars]
        @SerializedName("description")
        val description: String, // Pasca ditemukannya 1 orang positif terinfeksi virus Corona (Covid-19) awal April 2020 lalu, suasana pusat kota Banjar imbas Corona
        @SerializedName("publishedAt")
        val publishedAt: String, // 2020-04-13T04:25:38Z
        @SerializedName("source")
        val source: Source,
        @SerializedName("title")
        val title: String, // Imbas Corona, Perekonomian Warga Kota Banjar Lesu - galuh.id
        @SerializedName("url")
        val url: String, // https://galuh.id/imbas-corona-perekonomian-warga-kota-banjar-lesu/
        @SerializedName("urlToImage")
        val urlToImage: String // https://galuh.id/wp-content/uploads/2020/04/IMG-20200413-WA0022.jpg
    ) {
        data class Source(
            @SerializedName("id")
            val id: Any, // null
            @SerializedName("name")
            val name: String // Galuh.id
        )
    }
}