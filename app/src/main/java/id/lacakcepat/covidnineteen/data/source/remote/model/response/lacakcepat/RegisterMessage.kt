package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat


import com.google.gson.annotations.SerializedName

data class RegisterMessage(
    @SerializedName("id")
    val id: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("text")
    val text: String
)