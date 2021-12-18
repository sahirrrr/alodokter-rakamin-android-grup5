package com.rakamin.alodokter.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    companion object {
        const val KEY_LOGIN = "isLogin"
        const val KEY_TOKEN = "token"
        const val KEY_ID_USER = "idUser"
        const val KEY_ON_BOARDING = "onBoardingFinish"
    }

    private var prefLogin: SharedPreferences = context.getSharedPreferences("SessionLogin", Context.MODE_PRIVATE)
    private var editorLogin: SharedPreferences.Editor = prefLogin.edit()

    private var prefBoarding: SharedPreferences = context.getSharedPreferences("SessionOnBoarding", Context.MODE_PRIVATE)
    private var editorBoarding: SharedPreferences.Editor = prefBoarding.edit()

    fun createLoginSession() {
        editorLogin.putBoolean(KEY_LOGIN, true)
            .commit()
    }

    fun logout() {
        editorLogin.clear()
        editorLogin.commit()
    }

    val isLogin: Boolean = prefLogin.getBoolean(KEY_LOGIN, false)
    fun savToPreferences(key: String, value: Int) = editorLogin.putInt(key, value).commit()
    fun savToPreferences(key: String, value: String) = editorLogin.putString(key, value).commit()
    val idUserLogin: Int = prefLogin.getInt(KEY_ID_USER, 0)
    val tokenUser: String? = prefLogin.getString(KEY_TOKEN, null)

    fun createOnBoardingSession() {
        editorBoarding.putBoolean(KEY_ON_BOARDING, true)
            .commit()
    }

    val onBoardingFinish: Boolean = prefBoarding.getBoolean(KEY_ON_BOARDING, false)
}