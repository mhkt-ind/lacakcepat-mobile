package id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona

import com.google.gson.annotations.SerializedName

data class CountryCaseResponse(
    @SerializedName("meninggal")
    var death: String? = null,
    @SerializedName("positif")
    var positive: String? = null,
    @SerializedName("sembuh")
    var getWell: String? = null
)