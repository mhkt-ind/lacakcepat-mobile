package id.lacakcepat.covidnineteen.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "country_code")
data class CountryCodeEntity(
    @PrimaryKey @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "dial_code")
    val dialCode: String,
    @ColumnInfo(name = "name")
    val name: String
) : Parcelable