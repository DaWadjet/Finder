package com.example.finder.persistence



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finder.models.User

class UserDatabase {
    @Database(entities = [User::class], version = 1, exportSchema = false)
    abstract class CityDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
        companion object {
            @Volatile
            private var Instance: CityDatabase? = null

            fun getDatabase(context: Context): CityDatabase {
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                        // Setting this option in your app's database builder means that Room
                        // permanently deletes all data from the tables in your database when it
                        // attempts to perform a migration with no defined migration path.
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { Instance = it }
                }
            }
        }
    }
}