package id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona

import com.google.gson.annotations.SerializedName

data class GlobalDataCaseItem(
    @SerializedName("attributes")
    val attributes: GlobalDataAttribut?
)