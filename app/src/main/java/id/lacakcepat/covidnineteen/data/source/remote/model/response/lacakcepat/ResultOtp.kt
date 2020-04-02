package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat


import com.google.gson.annotations.SerializedName
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterData

data class ResultOtp(
    @SerializedName("data")
    val registerData: RegisterData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)