package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class ConditionsData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("health_condition")
    val healthCondition: String,
    @SerializedName("id_user")
    val idUser: String
)