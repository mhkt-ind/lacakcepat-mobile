package id.lacakcepat.covidnineteen.util

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreference @Inject constructor(private val sharedPref: SharedPreferences) {

    fun save(key: String, value: String) {
        sharedPref.edit { putString(key, value) }
    }

    fun save(key: String, value: Int) {
        sharedPref.edit { putInt(key, value) }
    }

    fun save(key: String, value: Boolean) {
        sharedPref.edit { putBoolean(key, value) }
    }

    fun getValueString(key: String) = sharedPref.getString(key, null)

    fun getValueInt(key: String) = sharedPref.getInt(key, 0)

    fun getValueBoolean(key: String, defValue: Boolean) = sharedPref.getBoolean(key, defValue)

    fun clearSharedPreference() {
        sharedPref.edit { clear() }
    }

    fun removeValue(key: String) {
        sharedPref.edit { remove(key) }
    }
}