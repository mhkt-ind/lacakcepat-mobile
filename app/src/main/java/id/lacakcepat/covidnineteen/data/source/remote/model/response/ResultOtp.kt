package id.lacakcepat.covidnineteen.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class ResultOtp(
    @SerializedName("data")
    val registerData: RegisterData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)