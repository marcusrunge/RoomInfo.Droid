package com.marcusrunge.roominfo.services

import android.content.Context
import android.content.SharedPreferences
import com.marcusrunge.roominfo.interfaces.ISettingsService

class SettingsServiceService private constructor(context: Context) : Any(),
    ISettingsService {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    override fun setSetting(key: String?, value: Any?) {
        when {
            value == null -> sharedPreferences.edit().putString(
                key,
                null
            ).apply()
            value.javaClass == Boolean::class.java -> sharedPreferences.edit().putBoolean(
                key,
                value as Boolean
            ).apply()
            value.javaClass == Int::class.java -> sharedPreferences.edit().putInt(
                key,
                value as Int
            ).apply()
            value.javaClass == Long::class.java -> sharedPreferences.edit().putLong(
                key,
                value as Long
            ).apply()
            value.javaClass == Float::class.java -> sharedPreferences.edit().putFloat(
                key,
                value as Float
            ).apply()
            value.javaClass == String::class.java -> sharedPreferences.edit().putString(
                key,
                value as String
            ).apply()
            else -> try {
                sharedPreferences.edit().putString(key, value.toString()).apply()
            } catch (ignored: Exception) {
            }
        }
    }

    override fun setSetting(key: String?, tClass: Class<*>?, value: Any?) {
        when {
            value == null -> sharedPreferences.edit().putString(
                key,
                null
            ).apply()
            tClass == Boolean::class.java -> sharedPreferences.edit().putBoolean(
                key,
                value as Boolean
            ).apply()
            tClass == Int::class.java -> sharedPreferences.edit().putInt(
                key,
                value as Int
            ).apply()
            tClass == Long::class.java -> sharedPreferences.edit().putLong(
                key,
                value as Long
            ).apply()
            tClass == Float::class.java -> sharedPreferences.edit().putFloat(
                key,
                value as Float
            ).apply()
            tClass == String::class.java -> sharedPreferences.edit().putString(
                key,
                value as String
            ).apply()
            else -> try {
                sharedPreferences.edit().putString(key, value.toString()).apply()
            } catch (ignored: Exception) {
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getSetting(tClass: Class<T>?, key: String?): T? {
        when (tClass) {
            Boolean::class.javaPrimitiveType -> return sharedPreferences.getBoolean(
                key,
                false
            ) as T
            Int::class.java -> return sharedPreferences.getInt(
                key,
                Int.MIN_VALUE
            ) as T
            Long::class.java -> return sharedPreferences.getLong(
                key,
                Long.MIN_VALUE
            ) as T
            Float::class.java -> return sharedPreferences.getFloat(
                key,
                Float.MIN_VALUE
            ) as T
            String::class.java -> return sharedPreferences.getString(
                key,
                ""
            ) as T
            else -> {
                try {
                    return sharedPreferences.getString(key, "") as T
                } catch (ignored: Exception) {
                }
            }
        }
        return null
    }

    override fun removeSetting(key: String?) {
        try {
            sharedPreferences.edit().remove(key).apply()
        } catch (ignored: Exception) {
        }
    }

    override fun hasSetting(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }

    /**
     * Clears all settings from memory
     */
    override fun clearSettings() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private var instance: ISettingsService? = null
        fun getInstance(context: Context): ISettingsService {
            if (instance == null) {
                instance = SettingsServiceService(context)
            }
            return instance as ISettingsService
        }
    }
}