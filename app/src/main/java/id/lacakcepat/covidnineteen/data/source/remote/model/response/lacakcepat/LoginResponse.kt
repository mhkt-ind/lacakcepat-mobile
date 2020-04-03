package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("otp_code")
    val otpCode: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("users_data")
    val loginData: LoginData,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)