package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val userId: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)