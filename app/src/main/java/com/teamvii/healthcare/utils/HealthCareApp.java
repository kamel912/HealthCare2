package com.teamvii.healthcare.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.teamvii.healthcare.R;

/**
 * Created by MK on 12/30/2017.
 */

public class HealthCareApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(base);
        if (sharedPreferences.contains(base.getString(R.string.preferences_language_key))) {
            String language = sharedPreferences.getString(base.getString(R.string.preferences_language_key), base.getString(R.string.preferences_language_arabic_value));
            super.attachBaseContext(LocaleHelper.onAttach(base, language));
        }
    }

}
