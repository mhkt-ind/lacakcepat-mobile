package id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat


import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)