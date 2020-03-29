package id.lacakcepat.covidnineteen.data.source.local.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import id.lacakcepat.covidnineteen.data.source.local.database.LacakCepatDatabase
import id.lacakcepat.covidnineteen.data.source.local.entity.CountryCodeEntity
import kotlinx.coroutines.coroutineScope

class SendDataBaseWorker(context: Context, workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    companion object {
        private val TAG = SendDataBaseWorker::class.java.simpleName
    }

    override suspend fun doWork(): Result = coroutineScope {
        try {
            @Suppress("BlockingMethodInNonBlockingContext")
            applicationContext.assets.open("kodenegara.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val kodeNegara = object : TypeToken<List<CountryCodeEntity>>(){}.type
                    val plantList: List<CountryCodeEntity> = Gson().fromJson(jsonReader, kodeNegara)

                    val database = LacakCepatDatabase.getInstance(applicationContext)
                    database.lacakCepatDao().insertCountryCodes(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}