package com.teamvii.healthcare.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.teamvii.healthcare.R;
import com.teamvii.healthcare.data.HealthCareDataSync;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        new HealthCareDataSync(this).syncAllData(this);
//        setupSharedPreferences();
    }

    public void findYourDR(View view) {
        Intent intent = new Intent(MainActivity.this, FindDoctorActivity.class);
        startActivity(intent);
    }

/*
    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String localeString = sharedPreferences.getString("language", getString(R.string.pref_language_arabic_value));
        String[] localeStrings = localeString.split("_");
        changeLocale(this,localeStrings[0]);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent startSettingsActivityIntent = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String localeString = sharedPreferences.getString("language", getString(R.string.pref_language_arabic_value));
        String[] localeStrings = localeString.split("_");
//        changeLocale(this,localeStrings[0]);
    }

/*
    public static void changeLocale(Context context, String language) {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!language.equals("") && !sysLocale.getLanguage().equals(language)) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }
    }
*/
}
