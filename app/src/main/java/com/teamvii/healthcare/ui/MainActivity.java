package com.teamvii.healthcare.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.teamvii.healthcare.R;
import com.teamvii.healthcare.connection.ApiClient;
import com.teamvii.healthcare.connection.ApiInterface;
import com.teamvii.healthcare.data.HealthCareDataSync;
import com.teamvii.healthcare.utils.ActivityManager;

public class MainActivity extends ActivityManager {
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        new HealthCareDataSync(this).syncAllData(this);
        apiInterface = ApiClient.getApiClient(defaultLanguage).create(ApiInterface.class);
    }

    public void findYourDR(View view) {
        Intent intent = new Intent(MainActivity.this, FindDoctorActivity.class);
        startActivity(intent);
    }


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
}
