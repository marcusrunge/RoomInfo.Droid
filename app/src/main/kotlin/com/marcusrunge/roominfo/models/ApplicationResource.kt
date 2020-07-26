package com.marcusrunge.roominfo.models

import android.content.Context
import com.marcusrunge.roominfo.MainActivity

data class ApplicationResource(
    var applicationContext: Context? = null,
    var mainActivity: MainActivity? = null
)