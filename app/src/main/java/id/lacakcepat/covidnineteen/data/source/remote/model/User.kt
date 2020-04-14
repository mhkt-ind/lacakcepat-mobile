package id.lacakcepat.covidnineteen.data.source.remote.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    @SerializedName("id_user")
    var id: String? = null,

    @Bindable
    @SerializedName("fullname")
    var fullName: String? = null,

    @Bindable
    @SerializedName("phone_number")
    var phoneNumber: String? = null,

    var token: String? = null,

    var otpCode: Int? = null

) : BaseObservable(), Parcelable