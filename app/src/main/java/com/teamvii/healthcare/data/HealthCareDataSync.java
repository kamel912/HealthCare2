package com.teamvii.healthcare.data;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.teamvii.healthcare.initiateVolley.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.teamvii.healthcare.data.HealthCareContract.AreasEntry;
import static com.teamvii.healthcare.data.HealthCareContract.GendersEntry;
import static com.teamvii.healthcare.data.HealthCareContract.InsurancesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.LanguagesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.SpecialitiesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.StatesEntry;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_AREA;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_GENDER;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_INSURANCE;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_LANGUAGE;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_SPECIALITY;
import static com.teamvii.healthcare.data.HealthCareContract.URL_SYNC_STATE;

/**
 * Created by MK on 12/28/2017.
 */

public class HealthCareDataSync {
    private static final String TAG = HealthCareDataSync.class.getSimpleName();
    private static Context context;

    public HealthCareDataSync(Context context) {
        this.context = context;
    }

    // Sync the database
    public static void syncAllData(Context context) {
        syncAreas(context);
        syncGenders(context);
        syncInsurances(context);
        syncLanguages(context);
        syncSpecialities(context);
        syncStates(context);
    }

    // Get data for areas table
    public static void syncAreas(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_AREA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    AreasEntry.CONTENT_URI,
                                    AreasEntry.TABLE_NAME,
                                    AreasEntry.COLUMN_AREA_ID,
                                    AreasEntry.COLUMN_AREA_NAME_EN,
                                    AreasEntry.COLUMN_AREA_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Get data for genders table
    public static void syncGenders(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_GENDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    GendersEntry.CONTENT_URI,
                                    GendersEntry.TABLE_NAME,
                                    GendersEntry.COLUMN_GENDER_ID,
                                    GendersEntry.COLUMN_GENDER_NAME_EN,
                                    GendersEntry.COLUMN_GENDER_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Get data for insurances table
    public static void syncInsurances(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_INSURANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    InsurancesEntry.CONTENT_URI,
                                    InsurancesEntry.TABLE_NAME,
                                    InsurancesEntry.COLUMN_INSURANCE_ID,
                                    InsurancesEntry.COLUMN_INSURANCE_NAME_EN,
                                    InsurancesEntry.COLUMN_INSURANCE_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Get data for languages table
    public static void syncLanguages(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_LANGUAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    LanguagesEntry.CONTENT_URI,
                                    LanguagesEntry.TABLE_NAME,
                                    LanguagesEntry.COLUMN_LANGUAGE_ID,
                                    LanguagesEntry.COLUMN_LANGUAGE_NAME_EN,
                                    LanguagesEntry.COLUMN_LANGUAGE_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Get data for specialities table
    public static void syncSpecialities(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_SPECIALITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    SpecialitiesEntry.CONTENT_URI,
                                    SpecialitiesEntry.TABLE_NAME,
                                    SpecialitiesEntry.COLUMN_SPECIALITY_ID,
                                    SpecialitiesEntry.COLUMN_SPECIALITY_NAME_EN,
                                    SpecialitiesEntry.COLUMN_SPECIALITY_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Get data for states table
    public static void syncStates(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SYNC_STATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            parseInsertData(
                                    jsonArray,
                                    StatesEntry.CONTENT_URI,
                                    StatesEntry.TABLE_NAME,
                                    StatesEntry.COLUMN_STATE_ID,
                                    StatesEntry.COLUMN_STATE_NAME_EN,
                                    StatesEntry.COLUMN_STATE_NAME_AR
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "message :" + error);
            }
        });
        MySingleton.getInstance(context).addRequestQueue(stringRequest);
    }

    // Parse the received data and put it in database
    public static void parseInsertData(JSONArray array, Uri CONTENT_URI, String TABLE_NAME, String COLUMN_ID, String COLUMN_NAME_EN, String COLUMN_NAME_AR) {
        ContentValues[] values = new ContentValues[array.length()];
        int numRowsInserted = 0;
        for (int i = 0; i < array.length(); i++) {
            JSONObject json;
            try {
                ContentValues value = new ContentValues();
                json = array.getJSONObject(i);
                String id = json.getString("id");
                String nameEn = json.getString("name_en");
                String nameAr = json.getString("name_ar");
                value.put(COLUMN_ID, id);
                value.put(COLUMN_NAME_EN, nameEn);
                value.put(COLUMN_NAME_AR, nameAr);
                values[i] = value;

                Log.d(TAG, "value from Area server : " + id + "  " + nameEn + "  " + nameAr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (values.length != 0) {
            numRowsInserted = context.getContentResolver().bulkInsert(CONTENT_URI, values);
        }
        Log.d(TAG, "Inserted rows in table" + TABLE_NAME + " count = " + Integer.toString(numRowsInserted));
    }

}
