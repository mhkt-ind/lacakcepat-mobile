package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("phone_number")
    val phoneNumber: Any
)