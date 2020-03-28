package id.lacakcepat.covidnineteen.data.source.remote.model.response

import com.google.gson.annotations.SerializedName

data class OTPData(
    @SerializedName("message")
    val message: List<OTPMessage>,
    @SerializedName("quota")
    val quota: String
)