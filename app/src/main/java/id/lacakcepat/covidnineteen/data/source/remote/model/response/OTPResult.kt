package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class OTPResult(
    @SerializedName("data")
    val otpData: OTPData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)