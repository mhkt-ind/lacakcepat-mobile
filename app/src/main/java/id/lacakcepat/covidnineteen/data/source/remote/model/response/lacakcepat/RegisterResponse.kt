package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat

import com.google.gson.annotations.SerializedName

data class RegisterResponse (

    @SerializedName("id_user")
    var userId: String? = null,

    @SerializedName("token")
    var token: String? = null,

    @SerializedName("otp_code")
    var otpCode: Int? = null,

    @SerializedName("code")
    var code: Int? = null

)