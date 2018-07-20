package com.example.meirlen.orc.helper;

/**
 * Created by Bagdat on 11/20/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;

    private static final String PREF_NAME = "Shop.KZ";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String USER_ID = "user_id";
    private static final String SHOW_INTRO = "show_intro";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }


    public void setAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, null);
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


    public boolean getShowIntro() {
        return pref.getBoolean(SHOW_INTRO, true);
    }
}
