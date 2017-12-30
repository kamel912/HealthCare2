package com.teamvii.healthcare.ui;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.teamvii.healthcare.R;
import com.teamvii.healthcare.data.HealthCareContract;
import com.teamvii.healthcare.utils.ActivityManager;

import static com.teamvii.healthcare.data.HealthCareContract.AreasEntry;
import static com.teamvii.healthcare.data.HealthCareContract.COLUMN_ID;
import static com.teamvii.healthcare.data.HealthCareContract.COLUMN_NAME;

public class FindDoctorActivity extends ActivityManager {
    Spinner test;
    TextView test_tv;
    Cursor areas, genders, insurances, languages, specialities, states;
    String areaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.find_doctor);

        test = findViewById(R.id.test);
        test_tv = findViewById(R.id.test_tv);

        setAdapter(getDataCursor(AreasEntry.CONTENT_URI), test, getString(R.string.select_area));
        if (areaId != null) {
            test_tv.setText(areaId);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Cursor getDataCursor(Uri uri) {
        String[] columns = {
                BaseColumns._ID,
                COLUMN_ID,
                COLUMN_NAME + defaultLanguage
        };
        Cursor cursor = getContentResolver().query(
                uri,
                columns,
                null,
                null,
                null
        );
        return cursor;
    }


    private void setAdapter(Cursor cursor, Spinner spinner, final String hint) {
        String[] from = {COLUMN_NAME + defaultLanguage};
        int[] to = {android.R.id.text1};
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{
                BaseColumns._ID,
                HealthCareContract.COLUMN_ID,
                HealthCareContract.COLUMN_NAME + defaultLanguage
        }
        );
        matrixCursor.addRow(new String[]{"0", "0", hint});
        Cursor[] cursors = {matrixCursor, cursor};
        Cursor finalCursor = new MergeCursor(cursors);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, finalCursor, from, to);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                    String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                    if (hint.equals(getString(R.string.select_area))){
                        areaId = id;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void testValues(View view) {
        if (areaId != null) {
            test_tv.setText(areaId);
        }
    }
}