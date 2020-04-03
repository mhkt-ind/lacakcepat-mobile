package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat


import com.google.gson.annotations.SerializedName

data class ConditionsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val conditionsData: ConditionsData,
    @SerializedName("status")
    val status: String
)