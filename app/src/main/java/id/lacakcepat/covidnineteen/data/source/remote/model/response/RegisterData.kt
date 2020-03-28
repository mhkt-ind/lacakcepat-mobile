package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id_user")
    val idUser: String
)