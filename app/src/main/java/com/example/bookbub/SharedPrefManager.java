package com.example.bookbub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager
{
    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_NAME = "name";
    private static final String KEY_NO = "no";
    private static final String KEY_PASSWD = "passwd";
    private static final String KEY_QUOTES_SHARED = "quotes_shared";
    private static final String KEY_BOOKS_SHARED = "books_shared";

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private SharedPrefManager(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_NO, user.getNo());
        editor.putString(KEY_PASSWD, user.getPasswd());
        editor.putInt(KEY_BOOKS_SHARED, user.getBooks_pub());
        editor.putInt(KEY_QUOTES_SHARED, user.getQuotes_shared());
        editor.apply();
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NO, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NO, null),
                sharedPreferences.getString(KEY_PASSWD, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getInt(KEY_BOOKS_SHARED, 0),
                sharedPreferences.getInt(KEY_QUOTES_SHARED, 0)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginFragment.class));
    }
}

