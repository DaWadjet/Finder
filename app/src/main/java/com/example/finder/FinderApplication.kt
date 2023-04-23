package com.example.finder

import android.app.Application
import com.example.finder.persistence.UserDatabase


@HiltAndroidApp
class FinderApplication : Application() {

    val database: UserDatabase by lazy { UserDatabase.UserDatabase.getDatabase(this)}
}