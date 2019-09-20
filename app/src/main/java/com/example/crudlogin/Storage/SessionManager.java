package com.example.crudlogin.Storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.crudlogin.LoginActivity;
import com.example.crudlogin.MainActivity;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static final String KEY_ID = "id";
    public static final String KEY_NAMA = "username";
    public static final String KEY_JK = "jk";


    private static final String is_login = "logginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager(Context context) {
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String id, String nama, String jk) {
        editor.putBoolean(is_login, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_JK, jk);
        editor.commit();
    }

    public HashMap getDetailLogin() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_ID, sp.getString(KEY_ID, null));
        map.put(KEY_NAMA, sp.getString(KEY_NAMA, null));
        map.put(KEY_JK, sp.getString(KEY_JK, null));

        return map;

    }

    public void logout() {
        editor.clear();
        editor.commit();
    }


    public void checkLogin() {
        if (!this.Loggin()) {
            Intent login = new Intent(_context, LoginActivity.class);
            _context.startActivity(login);
            ((MainActivity) _context).finish();
        }
    }

    public Boolean Loggin() {
        return sp.getBoolean(is_login, false);
    }


}
