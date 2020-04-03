package id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona

import com.google.gson.annotations.SerializedName

data class CountryDataCaseItem(
    @SerializedName("meninggal")
    val meninggal: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("positif")
    val positif: String?,
    @SerializedName("sembuh")
    val sembuh: String?
)