package com.marcusrunge.roominfo.interfaces

interface Settings {
    fun setSetting(key: String?, value: Any?)
    fun setSetting(
        key: String?,
        tClass: Class<*>?,
        value: Any?
    )

    fun <T> getSetting(tClass: Class<T>?, key: String?): T?
    fun removeSetting(key: String?)
    fun hasSetting(key: String?): Boolean
    fun clearSettings()
}