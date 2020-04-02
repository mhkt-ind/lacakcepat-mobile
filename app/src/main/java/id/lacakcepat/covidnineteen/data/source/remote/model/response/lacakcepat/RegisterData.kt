package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat


import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("message")
    val registerMessage: List<RegisterMessage>,
    @SerializedName("quota")
    val quota: String
)