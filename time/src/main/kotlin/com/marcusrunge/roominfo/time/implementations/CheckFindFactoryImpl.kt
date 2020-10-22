package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.CheckFind
import com.marcusrunge.roominfo.time.interfaces.CheckFindFactory

internal class CheckFindFactoryImpl {
    companion object : CheckFindFactory {
        var checkFind: CheckFind? = null
        override fun createSingleton(timeBase: TimeBase): CheckFind = when {
            checkFind != null -> checkFind!!
            else -> {
                checkFind = CheckFindImpl(timeBase)
                checkFind!!
            }
        }
    }
}