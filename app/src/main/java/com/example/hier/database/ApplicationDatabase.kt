package com.example.hier.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hier.models.Room

@Database(
    entities = [Room::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null

        // Singleton to get database. If it doesn't exist (= null), create it by calling buildDatabase()
        fun getDatabase(context: Context): ApplicationDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(appContext: Context) =
            androidx.room.Room.databaseBuilder(
                appContext,
                ApplicationDatabase::class.java,
                "hierDB"
            )
                .fallbackToDestructiveMigration().build()
    }
}