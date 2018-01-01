package com.teamvii.healthcare.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.teamvii.healthcare.R;
import com.teamvii.healthcare.utils.ActivityLanguageManager;

public class SplashActivity extends ActivityLanguageManager {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (PreferenceManager.getDefaultSharedPreferences(this).contains(getString(R.string.preferences_language_key))) {
            findViewById(R.id.choose_arabic).setVisibility(View.INVISIBLE);
            findViewById(R.id.choose_english).setVisibility(View.INVISIBLE);
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    public void chooseArabic(View view) {
        chooseLanguage(getString(R.string.preferences_language_arabic_value));
    }

    public void chooseEnglish(View view) {
        chooseLanguage(getString(R.string.preferences_language_english_value));
    }

    private void chooseLanguage(String language) {
//        defaultLanguage = language;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.preferences_language_key), language);
        editor.apply();
        startActivity(new Intent(this,MainActivity.class));
    }
}
