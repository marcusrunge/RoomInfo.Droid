package com.marcusrunge.roominfo.services

import android.content.Context
import android.content.SharedPreferences
import com.marcusrunge.roominfo.interfaces.Settings

class SettingsService private constructor(context: Context) : Any(),
    Settings {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    /**
     * Set setting based on parameter
     *
     * @param key   Unique value identifier
     * @param value Value to set or store
     */
    override fun setSetting(key: String, value: Any) {
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

    /**
     * Set setting based on parameter
     *
     * @param key    Unique value identifier
     * @param tClass The class of type of the object to be inserted. Allowed are: Boolean.class, Integer.class, Long.class, Float.class and String.class
     * @param value  Value to set or store
     */
    override fun setSetting(
        key: String,
        tClass: Class<*>,
        value: Any
    ) {
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

    /**
     * Retrieve setting based on parameter
     *
     * @param tClass The class of type of the expected object to be returned. Allowed are: Boolean.class, Integer.class, Long.class, Float.class and String.class
     * @param key    The unique key of the value
     * @param <T>    The type of the expected object to be returned e.g. "boolean.class"
     * @return
    </T> */
    @Suppress("UNCHECKED_CAST")
    override fun <T> getSetting(tClass: Class<T>, key: String): T? {
        if (tClass == Boolean::class.javaPrimitiveType) return sharedPreferences.getBoolean(
            key,
            false
        ) as T else if (tClass == Int::class.java) return sharedPreferences.getInt(
            key,
            Int.MIN_VALUE
        ) as T else if (tClass == Long::class.java) return sharedPreferences.getLong(
            key,
            Long.MIN_VALUE
        ) as T else if (tClass == Float::class.java) return sharedPreferences.getFloat(
            key,
            Float.MIN_VALUE
        ) as T else if (tClass == String::class.java) return sharedPreferences.getString(
            key,
            ""
        ) as T else {
            try {
                return sharedPreferences.getString(key, "") as T
            } catch (ignored: Exception) {
            }
        }
        return null
    }

    /**
     * Remove setting based on key
     *
     * @param key Unique value identifier
     */
    override fun removeSetting(key: String) {
        try {
            sharedPreferences.edit().remove(key).apply()
        } catch (ignored: Exception) {
        }
    }

    /**
     * Retrieve whether a setting exists
     *
     * @param key The unique key of the value
     * @return
     */
    override fun hasSetting(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    /**
     * Clears all settings from memory
     */
    override fun clearSettings() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private var instance: Settings? = null
        fun getInstance(context: Context): Settings {
            if (instance == null) {
                instance = SettingsService(context)
            }
            return instance as Settings
        }
    }

}