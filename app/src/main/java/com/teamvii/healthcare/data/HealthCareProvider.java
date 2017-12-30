package com.teamvii.healthcare.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.teamvii.healthcare.data.HealthCareContract.AreasEntry;
import static com.teamvii.healthcare.data.HealthCareContract.COLUMN_ID;
import static com.teamvii.healthcare.data.HealthCareContract.CONTENT_AUTHORITY;
import static com.teamvii.healthcare.data.HealthCareContract.GendersEntry;
import static com.teamvii.healthcare.data.HealthCareContract.InsurancesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.LanguagesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.SpecialitiesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.StatesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.TABLES_NAMES;

/**
 * Created by MK on 12/26/2017.
 */

public class HealthCareProvider extends ContentProvider {
    public static final int CODE_AREA = 100;
    public static final int CODE_GENDER = 200;
    public static final int CODE_INSURANCE = 300;
    public static final int CODE_LANGUAGE = 400;
    public static final int CODE_SPECIALITY = 500;
    public static final int CODE_STATE = 600;
    public static final int CODE_AREA_WITH_ID = 101;
    public static final int CODE_GENDER_WITH_ID = 201;
    public static final int CODE_INSURANCE_WITH_ID = 301;
    public static final int CODE_LANGUAGE_WITH_ID = 401;
    public static final int CODE_SPECIALITY_WITH_ID = 501;
    public static final int CODE_STATE_WITH_ID = 601;
    public static final int[] TABLES_CODES = {
            //  To add codes of tables to TABLES_CODES
            CODE_AREA,
            CODE_GENDER,
            CODE_INSURANCE,
            CODE_LANGUAGE,
            CODE_SPECIALITY,
            CODE_STATE
    };
    public static final int[] TABLES_CODES_WITH_ID = {
            //  To add codes of tables with ids to TABLES_CODES_WITH_ID
            CODE_AREA_WITH_ID,
            CODE_GENDER_WITH_ID,
            CODE_INSURANCE_WITH_ID,
            CODE_LANGUAGE_WITH_ID,
            CODE_SPECIALITY_WITH_ID,
            CODE_STATE_WITH_ID
    };
    public static UriMatcher matcher = buildUriMatcher();


    HealthCareDbHelper mDbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        for (int i = 0; i < TABLES_NAMES.size(); i++) {
            String TABLE_NAME = TABLES_NAMES.get(i);
            Integer TABLE_CODE = TABLES_CODES[i];
            Integer TABLE_CODE_WITH_ID = TABLES_CODES_WITH_ID[i];
            uriMatcher.addURI(CONTENT_AUTHORITY, TABLE_NAME, TABLE_CODE);
            uriMatcher.addURI(CONTENT_AUTHORITY, TABLE_NAME + "/#", TABLE_CODE_WITH_ID);
        }
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDbHelper = new HealthCareDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selections, String[] selectionArgs, String sortOrder) {
        int match = matcher.match(uri);

        switch (match) {
            case CODE_AREA:
                return tableBulkSelect(AreasEntry.TABLE_NAME, uri, projection);
            case CODE_AREA_WITH_ID:
                return tableRowSelect(AreasEntry.TABLE_NAME, uri, projection, selections);
            case CODE_GENDER:
                return tableBulkSelect(GendersEntry.TABLE_NAME, uri, projection);
            case CODE_GENDER_WITH_ID:
                return tableRowSelect(GendersEntry.TABLE_NAME, uri, projection, selections);
            case CODE_INSURANCE:
                return tableBulkSelect(InsurancesEntry.TABLE_NAME, uri, projection);
            case CODE_INSURANCE_WITH_ID:
                return tableRowSelect(InsurancesEntry.TABLE_NAME, uri, projection, selections);
            case CODE_LANGUAGE:
                return tableBulkSelect(LanguagesEntry.TABLE_NAME, uri, projection);
            case CODE_LANGUAGE_WITH_ID:
                return tableRowSelect(LanguagesEntry.TABLE_NAME, uri, projection, selections);
            case CODE_SPECIALITY:
                return tableBulkSelect(SpecialitiesEntry.TABLE_NAME, uri, projection);
            case CODE_SPECIALITY_WITH_ID:
                return tableRowSelect(SpecialitiesEntry.TABLE_NAME, uri, projection, selections);
            case CODE_STATE:
                return tableBulkSelect(StatesEntry.TABLE_NAME, uri, projection);
            case CODE_STATE_WITH_ID:
                return tableRowSelect(StatesEntry.TABLE_NAME, uri, projection, selections);
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("We are not implementing getType in HealthCare");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        throw new RuntimeException("We are not implementing insert in HealthCare");
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        int match = matcher.match(uri);
        switch (match) {
            case CODE_AREA:
                return tableBulkInsert(AreasEntry.TABLE_NAME, values, uri);
            case CODE_GENDER:
                return tableBulkInsert(GendersEntry.TABLE_NAME, values, uri);
            case CODE_INSURANCE:
                return tableBulkInsert(InsurancesEntry.TABLE_NAME, values, uri);
            case CODE_LANGUAGE:
                return tableBulkInsert(LanguagesEntry.TABLE_NAME, values, uri);
            case CODE_SPECIALITY:
                return tableBulkInsert(SpecialitiesEntry.TABLE_NAME, values, uri);
            case CODE_STATE:
                return tableBulkInsert(StatesEntry.TABLE_NAME, values, uri);
            default:
                return super.bulkInsert(uri, values);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = matcher.match(uri);
        switch (match) {
            case CODE_AREA:
                return tableBulkDelete(AreasEntry.TABLE_NAME, uri);
            case CODE_AREA_WITH_ID:
                return tableRowDelete(AreasEntry.TABLE_NAME, uri);
            case CODE_GENDER:
                return tableBulkDelete(GendersEntry.TABLE_NAME, uri);
            case CODE_GENDER_WITH_ID:
                return tableRowDelete(GendersEntry.TABLE_NAME, uri);
            case CODE_INSURANCE:
                return tableBulkDelete(InsurancesEntry.TABLE_NAME, uri);
            case CODE_INSURANCE_WITH_ID:
                return tableRowDelete(InsurancesEntry.TABLE_NAME, uri);
            case CODE_LANGUAGE:
                return tableBulkDelete(LanguagesEntry.TABLE_NAME, uri);
            case CODE_LANGUAGE_WITH_ID:
                return tableRowDelete(LanguagesEntry.TABLE_NAME, uri);
            case CODE_SPECIALITY:
                return tableBulkDelete(SpecialitiesEntry.TABLE_NAME, uri);
            case CODE_SPECIALITY_WITH_ID:
                return tableRowDelete(SpecialitiesEntry.TABLE_NAME, uri);
            case CODE_STATE:
                return tableBulkDelete(StatesEntry.TABLE_NAME, uri);
            case CODE_STATE_WITH_ID:
                return tableRowDelete(StatesEntry.TABLE_NAME, uri);
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw new RuntimeException("We are not implementing update in HealthCare");
    }

    private Cursor tableBulkSelect(String TABLE_NAME, Uri uri, String[] columns) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();
        Cursor mCursor;
        String mSortOrder = COLUMN_ID + " ASC";
        mCursor = sqLiteDatabase.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                mSortOrder);
        mCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return mCursor;
    }

    private Cursor tableRowSelect(String TABLE_NAME, Uri uri, String[] columns, String selection) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();
        Cursor mCursor;
        String[] mSelectionArgs;
        String id = uri.getPathSegments().get(1);
        mSelectionArgs = new String[]{id};
        mCursor = sqLiteDatabase.query(
                TABLE_NAME,
                columns,
                selection + "=?",
                mSelectionArgs,
                null,
                null,
                null);
        mCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return mCursor;
    }

    private int tableBulkInsert(String TABLE_NAME, ContentValues[] values, Uri uri) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
        int rowsInserted = 0;
        sqLiteDatabase.beginTransaction();
        try {
            for (ContentValues value : values) {
                long _id = sqLiteDatabase.insert(TABLE_NAME, null, value);
                if (_id != -1) {
                    rowsInserted++;
                }
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
        if (rowsInserted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsInserted;
    }

    private int tableBulkDelete(String TABLE_NAME, Uri uri) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
        int numRowsDeleted = sqLiteDatabase.delete(
                TABLE_NAME,
                null,
                null);
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    private int tableRowDelete(String TABLE_NAME, Uri uri) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
        String id = uri.getPathSegments().get(1);
        String[] mSelectionArgs = new String[]{id};
        int numRowsDeleted = sqLiteDatabase.delete(
                TABLE_NAME,
                "id=?",
                mSelectionArgs);
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }
}
