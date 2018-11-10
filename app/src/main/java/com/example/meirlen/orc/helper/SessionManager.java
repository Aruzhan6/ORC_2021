package com.example.meirlen.orc.helper;

/**
 * Created by Bagdat on 11/20/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class SessionManager {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;

    private static final String PREF_NAME = "Shop.KZ";
    private static final String ACCESS_TOKEN = "accessToken";

    private static final String LOCAL_CAT = "locCat";


    private static final String PHONE_NUMBER = "phone";
    private static final String USER_NAME = "user_name";
    private static final String USER_ID = "user_id";
    private static final String SHOW_INTRO = "show_intro";
    private static final String SHOW_HINT_SWIPE = "show_swipe";


    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String FLAT = "flat";
    private static final String ENTRANCE = "entrance";
    private static final String FLOOR = "floor";
    private static final String LAT = "lat";
    private static final String LOT = "lot";


    //filter

    private static final String KEY_WORD = "key_word";
    private static final String FROM = "from";
    private static final String TO = "fromTo";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }


    public void setAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public void setPhone(String number) {
        editor.putString(PHONE_NUMBER, number);
        editor.commit();
    }

    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public void setLocalCat(String lat) {
        editor.putString(LOCAL_CAT, lat);
        editor.commit();
    }

    public String getLocalCat() {
        return pref.getString(LOCAL_CAT, null);
    }

    public void setLot(String lat) {
        editor.putString(LOT, lat);
        editor.commit();
    }

    public String getLot() {
        return pref.getString(LOT, null);
    }

    public void setLat(String lat) {
        editor.putString(LAT, lat);
        editor.commit();
    }

    public String getLat() {
        return pref.getString(LAT, null);
    }

    public String getUserName() {
        return pref.getString(USER_NAME, null);
    }

    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, null);
    }

    public String getPhone() {
        return pref.getString(PHONE_NUMBER, null);
    }

    public void setUserId(String userId) {
        editor.putString(USER_ID, userId);

        editor.commit();
    }

    public String getUserId() {
        return pref.getString(USER_ID, null);
    }


    public void setShowIntro(boolean CountryJson) {
        editor.putBoolean(SHOW_INTRO, CountryJson);
        editor.commit();
    }

    public void setShowSwipe(boolean CountryJson) {
        editor.putBoolean(SHOW_HINT_SWIPE, CountryJson);
        editor.commit();
    }

    public boolean getShowIntro() {
        return pref.getBoolean(SHOW_INTRO, true);
    }


    public boolean getShowSwipe() {
        return pref.getBoolean(SHOW_HINT_SWIPE, true);
    }

    public void setStreet(String CountryJson) {
        editor.putString(STREET, CountryJson);
        editor.commit();
    }

    public void setHouse(String CountryJson) {
        editor.putString(HOUSE, CountryJson);
        editor.commit();
    }

    public void setFlat(String CountryJson) {
        editor.putString(FLAT, CountryJson);
        editor.commit();
    }

    public void setFloor(String CountryJson) {
        editor.putString(FLOOR, CountryJson);
        editor.commit();
    }

    public void setEntrance(String CountryJson) {
        editor.putString(ENTRANCE, CountryJson);
        editor.commit();
    }

    public String getEntrance() {
        return pref.getString(ENTRANCE, null);
    }


    public String getStreet() {
        return pref.getString(STREET, null);
    }

    public String getHouse() {
        return pref.getString(HOUSE, null);
    }

    public String getFlat() {
        return pref.getString(FLAT, null);
    }


    public String getFloor() {
        return pref.getString(FLOOR, null);
    }


    public void setFrom(String CountryJson) {
        editor.putString(FROM, CountryJson);
        editor.commit();
    }

    public String getFrom() {
        return pref.getString(FROM, null);
    }

    public void setTo(String CountryJson) {
        editor.putString(TO, CountryJson);
        editor.commit();
    }

    public String getTo() {
        return pref.getString(TO, null);
    }

    public void setKeyWord(String CountryJson) {
        editor.putString(KEY_WORD, CountryJson);
        editor.commit();
    }

    public String getKeyWord() {
        return pref.getString(KEY_WORD, null);
    }


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void clearSession(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
