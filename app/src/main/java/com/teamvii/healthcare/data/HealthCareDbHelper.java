package com.teamvii.healthcare.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;

import com.teamvii.healthcare.R;

import java.util.List;
import java.util.Locale;

import static com.teamvii.healthcare.data.HealthCareContract.TABLES_COLUMNS;
import static com.teamvii.healthcare.data.HealthCareContract.TABLES_NAMES;

/**
 * Created by MK on 12/26/2017.
 */

public class HealthCareDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HealthCare.db";

    private static final int DATABASE_VERSION = 1;
    private  Context mCONTEXT;


    public HealthCareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCONTEXT = context;
    }

    public final static String createTableSqlQuery(String TABLE_NAME, List<String> COLUMNS, String COLUMN_TIMESTAMP) {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int i = 0; i < COLUMNS.size(); i++) {
            sqlQuery = sqlQuery + COLUMNS.get(i) + " TEXT UNIQUE NOT NULL, ";
        }
        sqlQuery = sqlQuery + COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        return sqlQuery;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < TABLES_NAMES.size(); i++) {
            String TABLE_NAME = TABLES_NAMES.get(i);
            List<String> COLUMNS = TABLES_COLUMNS.get(i);
            sqLiteDatabase.execSQL(
                    createTableSqlQuery(
                            TABLE_NAME,
                            COLUMNS,
                            HealthCareContract.COLUMN_TIMESTAMP
                    )
            );

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (String TABLE_NAME : TABLES_NAMES) {
            sqLiteDatabase.execSQL(
                    "DROP TABLE IF EXISTS  " +
                            TABLE_NAME
            );
        }
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mCONTEXT);
        String localeString = sharedPreferences.getString("language", mCONTEXT.getString(R.string.preferences_language_arabic_value));
        String[] finalLocaleString = localeString.split("_");
        Locale locale = new Locale(finalLocaleString[0],finalLocaleString[1]);
        sqLiteDatabase.setLocale(locale);
    }

}
