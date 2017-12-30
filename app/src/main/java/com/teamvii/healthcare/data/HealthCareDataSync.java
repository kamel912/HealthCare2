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

import java.util.List;

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
                                    AreasEntry.AREAS_TABLE_COLUMNS
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
                                    GendersEntry.GENDERS_TABLE_COLUMNS
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
                                    InsurancesEntry.INSURANCES_TABLE_COLUMNS
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
                                    LanguagesEntry.LANGUAGES_TABLE_COLUMNS
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
                                    SpecialitiesEntry.SPECIALITIES_TABLE_COLUMNS
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
                                    StatesEntry.STATES_TABLE_COLUMNS
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
    public static void parseInsertData(JSONArray array, Uri CONTENT_URI, String TABLE_NAME, List<String> COLUMNS) {
        ContentValues[] values = new ContentValues[array.length()];
        int numRowsInserted = 0;
        for (int i = 0; i < array.length(); i++) {
            JSONObject json;
            try {
                ContentValues value = new ContentValues();
                json = array.getJSONObject(i);
                for (String COLUMN_NAME : COLUMNS){
                    String COLUMN_VALUE = json.getString(COLUMN_NAME);
                    value.put(COLUMN_NAME,COLUMN_VALUE);
                    Log.d(TAG, "value from Area server { " + COLUMN_NAME + " : " + COLUMN_VALUE +"}" );
                }

                values[i] = value;


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (values.length != 0) {
            int numRowsDeleted = context.getContentResolver().delete(CONTENT_URI,null,null);
            numRowsInserted = context.getContentResolver().bulkInsert(CONTENT_URI, values);
        }
        Log.d(TAG, "Inserted rows in table { " + TABLE_NAME + " } count = " + Integer.toString(numRowsInserted));
    }

}
