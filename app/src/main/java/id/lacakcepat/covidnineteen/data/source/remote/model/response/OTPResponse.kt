package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class OTPResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("otp_code")
    val otpCode: Int,
    @SerializedName("result")
    val result: OTPResult,
    @SerializedName("status")
    val status: String
)