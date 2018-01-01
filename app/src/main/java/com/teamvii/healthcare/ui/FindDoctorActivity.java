package com.teamvii.healthcare.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.teamvii.healthcare.R;
import com.teamvii.healthcare.utils.ActivityLanguageManager;

import static com.teamvii.healthcare.data.HealthCareContract.AreasEntry;
import static com.teamvii.healthcare.data.HealthCareContract.COLUMN_ID;
import static com.teamvii.healthcare.data.HealthCareContract.COLUMN_NAME;
import static com.teamvii.healthcare.data.HealthCareContract.GendersEntry;
import static com.teamvii.healthcare.data.HealthCareContract.InsurancesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.LanguagesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.SpecialitiesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.StatesEntry;

public class FindDoctorActivity extends ActivityLanguageManager {

    TextView area_id, gender_id, insurance_id, language_id, speciality_id, state_id;
    Spinner areas, genders, insurances, languages, specialities, states;
    String areaId, genderId, insuranceId, languageId, specialityId, stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.find_doctor);

        areas = findViewById(R.id.areas);
        genders = findViewById(R.id.genders);
        insurances = findViewById(R.id.insurances);
        languages = findViewById(R.id.languages);
        specialities = findViewById(R.id.specialities);
        states = findViewById(R.id.states);



        area_id = findViewById(R.id.area_id);
        gender_id = findViewById(R.id.gender_id);
        insurance_id = findViewById(R.id.insurance_id);
        language_id = findViewById(R.id.language_id);
        speciality_id = findViewById(R.id.speciality_id);
        state_id = findViewById(R.id.state_id);

        setupAdapter(AreasEntry.CONTENT_URI, areas, getString(R.string.select_area));
        setupAdapter(GendersEntry.CONTENT_URI, genders, getString(R.string.select_gender));
        setupAdapter(InsurancesEntry.CONTENT_URI, insurances, getString(R.string.select_insurance));
        setupAdapter(LanguagesEntry.CONTENT_URI, languages, getString(R.string.select_language));
        setupAdapter(SpecialitiesEntry.CONTENT_URI, specialities, getString(R.string.select_speciality));
        setupAdapter(StatesEntry.CONTENT_URI, states, getString(R.string.select_state));
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
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                Intent startSettingsActivityIntent = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setupAdapter(Uri uri, Spinner spinner, String hint) {
        Cursor cursor = setupCursor(uri, hint);
        String[] from = {COLUMN_NAME + defaultLanguage};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to);
        spinner.setAdapter(adapter);
        setupOnItemSelection(spinner, hint);
    }

    private Cursor setupCursor(Uri uri, String hint) {
        Cursor cursor = setupCursorData(uri);
        cursor = setupCursorHints(cursor, hint);
        return cursor;
    }

    private Cursor setupCursorData(Uri uri) {
        String[] columns = getColumns(uri,null);
        Cursor cursor = getContentResolver().query(
                uri,
                columns,
                null,
                null,
                null
        );
        return cursor;
    }
    private String[] getColumns(Uri uri, String hint){
        String[] columns;
        if (uri.equals(AreasEntry.CONTENT_URI) || hint.equals(getString(R.string.select_area))){
            columns = new String[]{
                    BaseColumns._ID,
                    COLUMN_ID,
                    COLUMN_NAME + defaultLanguage
            };
        }else {
            columns = new String[]{
                    BaseColumns._ID,
                    COLUMN_ID,
                    COLUMN_NAME + defaultLanguage
            };
        }
        return columns;
    }

    private Cursor setupCursorHints(Cursor cursor, String hint) {
        String[] columns = getColumns(null,hint);
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        matrixCursor.addRow(new String[]{"0", "0", hint});
        Cursor[] cursors = {matrixCursor, cursor};
        return new MergeCursor(cursors);
    }

    private void setupOnItemSelection(Spinner spinner, final String hint) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    if (hint.equals(getString(R.string.select_area))) {
                        areaId = null;
                    } else if (hint.equals(getString(R.string.select_gender))) {
                        genderId = null;
                    } else if (hint.equals(getString(R.string.select_insurance))) {
                        insuranceId = null;
                    } else if (hint.equals(getString(R.string.select_language))) {
                        languageId = null;
                    } else if (hint.equals(getString(R.string.select_speciality))) {
                        specialityId = null;
                    } else if (hint.equals(getString(R.string.select_state))) {
                        stateId = null;
                    }
                } else if (i > 0) {
                    Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                    String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                    if (hint.equals(getString(R.string.select_area))) {
                        areaId = id;
                    } else if (hint.equals(getString(R.string.select_gender))) {
                        genderId = id;
                    } else if (hint.equals(getString(R.string.select_insurance))) {
                        insuranceId = id;
                    } else if (hint.equals(getString(R.string.select_language))) {
                        languageId = id;
                    } else if (hint.equals(getString(R.string.select_speciality))) {
                        specialityId = id;
                    } else if (hint.equals(getString(R.string.select_state))) {
                        stateId = id;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void testValues(View view) {
        area_id.setText(areaId);
        gender_id.setText(genderId);
        insurance_id.setText(insuranceId);
        language_id.setText(languageId);
        speciality_id.setText(specialityId);
        state_id.setText(stateId);
    }
}