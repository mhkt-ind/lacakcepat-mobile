package id.lacakcepat.covidnineteen.utilities

import id.lacakcepat.covidnineteen.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    companion object {
        private const val URL = "https://api.kawalcorona.com/"
        private var retrofit: Retrofit? = null
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }

}