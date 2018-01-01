package com.teamvii.healthcare.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MK on 12/30/2017.
 */

public class ActivityLanguageManager extends AppCompatActivity {

    public String defaultLanguage;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultLanguage = LocaleHelper.getLanguage(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (defaultLanguage != null && !defaultLanguage.equals(LocaleHelper.getLanguage(this))) {
            recreate();
        }
    }

}
