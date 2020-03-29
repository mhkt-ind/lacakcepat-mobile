package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: RegisterData,
    @SerializedName("status")
    val status: String
)