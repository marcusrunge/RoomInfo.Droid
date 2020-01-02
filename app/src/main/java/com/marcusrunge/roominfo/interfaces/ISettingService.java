package com.marcusrunge.roominfo.interfaces;

public interface ISettingService {
    void setSetting(String key, Object value);

    void setSetting(String key, Class tClass, Object value);

    <T> T getSetting(Class<T> tClass, String key);

    void removeSetting(String key);

    boolean hasSetting(String key);

    void clearSettings();
}
