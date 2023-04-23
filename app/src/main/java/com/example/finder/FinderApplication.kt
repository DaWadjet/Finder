package com.example.finder

class FinderApplication : Application() {

    val database: CityDatabase by lazy { CityDatabase.getDatabase(this) }
}