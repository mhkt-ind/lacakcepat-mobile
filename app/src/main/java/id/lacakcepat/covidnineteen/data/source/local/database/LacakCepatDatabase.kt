package id.lacakcepat.covidnineteen.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import id.lacakcepat.covidnineteen.data.source.local.entity.CountryCodeEntity
import id.lacakcepat.covidnineteen.data.source.local.workers.SendDataBaseWorker

@Database(entities = [CountryCodeEntity::class], version = 1, exportSchema = false)
abstract class LacakCepatDatabase : RoomDatabase() {

    abstract fun lacakCepatDao(): LacakCepatDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LacakCepatDatabase? = null

        fun getInstance(context: Context): LacakCepatDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        private fun buildDatabase(context: Context): LacakCepatDatabase {
            return Room.databaseBuilder(context, LacakCepatDatabase::class.java, "lacak_cepat_db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SendDataBaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}