package com.marcusrunge.roominfo.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.marcusrunge.roominfo.interfaces.ISettingService;

public class SettingService extends Object implements ISettingService {
    private static ISettingService _settingServiceInstance;
    private final SharedPreferences _sharedPreferences;

    private SettingService(Context context) {
        _sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }

    public static ISettingService getInstance(Context context) {
        if (_settingServiceInstance == null) {
            _settingServiceInstance = new SettingService(context);
        }
        return _settingServiceInstance;
    }

    /**
     * Set setting based on parameter
     *
     * @param key   Unique value identifier
     * @param value Value to set or store
     */
    @Override
    public void setSetting(String key, Object value) {
        if (value == null)
            _sharedPreferences.edit().putString(key, null).apply();
        else if (value.getClass() == Boolean.class)
            _sharedPreferences.edit().putBoolean(key, (boolean) value).apply();
        else if (value.getClass() == Integer.class)
            _sharedPreferences.edit().putInt(key, (int) value).apply();
        else if (value.getClass() == Long.class)
            _sharedPreferences.edit().putLong(key, (long) value).apply();
        else if (value.getClass() == Float.class)
            _sharedPreferences.edit().putFloat(key, (float) value).apply();
        else if (value.getClass() == String.class)
            _sharedPreferences.edit().putString(key, (String) value).apply();
        else try {
                _sharedPreferences.edit().putString(key, String.valueOf(value)).apply();
            } catch (Exception ignored) {
            }
    }

    /**
     * Set setting based on parameter
     *
     * @param key    Unique value identifier
     * @param tClass The class of type of the object to be inserted. Allowed are: Boolean.class, Integer.class, Long.class, Float.class and String.class
     * @param value  Value to set or store
     */
    @Override
    public void setSetting(String key, Class tClass, Object value) {
        if (value == null)
            _sharedPreferences.edit().putString(key, null).apply();
        else if (tClass == Boolean.class)
            _sharedPreferences.edit().putBoolean(key, (boolean) value).apply();
        else if (tClass == Integer.class)
            _sharedPreferences.edit().putInt(key, (int) value).apply();
        else if (tClass == Long.class)
            _sharedPreferences.edit().putLong(key, (long) value).apply();
        else if (tClass == Float.class)
            _sharedPreferences.edit().putFloat(key, (float) value).apply();
        else if (tClass == String.class)
            _sharedPreferences.edit().putString(key, (String) value).apply();
        else try {
                _sharedPreferences.edit().putString(key, String.valueOf(value)).apply();
            } catch (Exception ignored) {
            }
    }

    /**
     * Retrieve setting based on parameter
     *
     * @param tClass The class of type of the expected object to be returned. Allowed are: Boolean.class, Integer.class, Long.class, Float.class and String.class
     * @param key    The unique key of the value
     * @param <T>    The type of the expected object to be returned e.g. "boolean.class"
     * @return
     */
    @SuppressWarnings(value = "unchecked")
    @Override
    public <T> T getSetting(Class<T> tClass, String key) {

        if (tClass == boolean.class)
            return (T) (Object) _sharedPreferences.getBoolean(key, false);
        else if (tClass == Integer.class)
            return (T) (Object) _sharedPreferences.getInt(key, Integer.MIN_VALUE);
        else if (tClass == Long.class)
            return (T) (Object) _sharedPreferences.getLong(key, Long.MIN_VALUE);
        else if (tClass == Float.class)
            return (T) (Object) _sharedPreferences.getFloat(key, Float.MIN_VALUE);
        else if (tClass == String.class)
            return (T) _sharedPreferences.getString(key, "");
        else {
            try {
                return (T) _sharedPreferences.getString(key, "");
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    /**
     * Remove setting based on key
     *
     * @param key Unique value identifier
     */
    @Override
    public void removeSetting(String key) {
        try {
            _sharedPreferences.edit().remove(key).apply();

        } catch (Exception ignored) {
        }
    }

    /**
     * Retrieve whether a setting exists
     *
     * @param key The unique key of the value
     * @return
     */
    @Override
    public boolean hasSetting(String key) {
        return _sharedPreferences.contains(key);
    }

    /**
     * Clears all settings from memory
     */
    @Override
    public void clearSettings() {
        _sharedPreferences.edit().clear().apply();
    }
}
