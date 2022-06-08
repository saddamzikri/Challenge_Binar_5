package com.saddam.challengebinar5.helper

import android.content.Context
import android.preference.PreferenceManager

class Preferences {
    companion object {
        const val USER_LOGIN = "user_login"
        const val LOGIN_STATUS = "login_status"
    }

    private fun getSharedPreference(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun setLoggedInUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(USER_LOGIN, username)
        editor.apply()
    }

    fun getLoggedInUser(context: Context) =
        getSharedPreference(context).getString(USER_LOGIN, "")

    fun setLoggedInStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(LOGIN_STATUS, status)
        editor.apply()
    }

    fun getLoggedInStatus(context: Context) =
        getSharedPreference(context).getBoolean(LOGIN_STATUS, false)

    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(USER_LOGIN)
        editor.remove(LOGIN_STATUS)
        editor.apply()
    }
}