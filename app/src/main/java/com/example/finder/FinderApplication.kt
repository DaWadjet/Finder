package com.example.finder

import android.app.Application
import com.example.finder.persistence.UserDatabase.*
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class FinderApplication : Application()