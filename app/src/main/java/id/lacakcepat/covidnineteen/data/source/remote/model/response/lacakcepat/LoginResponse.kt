package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat

import com.google.gson.annotations.SerializedName
import id.lacakcepat.covidnineteen.data.source.remote.model.User

data class LoginResponse(

    @SerializedName("token")
    var token: String? = null,

    @SerializedName("otp_code")
    var otpCode: Int? = null,

    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("user_data")
    var user: User

)