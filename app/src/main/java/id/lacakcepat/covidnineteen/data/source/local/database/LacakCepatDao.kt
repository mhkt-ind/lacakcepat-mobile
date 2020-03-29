package id.lacakcepat.covidnineteen.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import id.lacakcepat.covidnineteen.data.source.local.entity.CountryCodeEntity

@Dao
interface LacakCepatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryCodes(countryCodes: List<CountryCodeEntity>)
}