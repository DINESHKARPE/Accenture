package com.assignment

import android.app.Application
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi

open class AssignmentApp : Application() {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate() {
        super.onCreate()
    }


}