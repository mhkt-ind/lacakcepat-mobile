package id.lacakcepat.covidnineteen.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("otp_code")
    val otpCode: Int,
    @SerializedName("result_otp")
    val resultOtp: ResultOtp,
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("message")
    val message: String
)