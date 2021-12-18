package com.rakamin.alodokter.session

class SessionRepository(private val session: SessionManager) {

    fun loginUser(token: String, idUser: Int) {
        session.createLoginSession()
        session.savToPreferences(SessionManager.KEY_ID_USER, idUser)
        session.savToPreferences(SessionManager.KEY_TOKEN, token)
    }

    fun getIdUser(): Int = session.idUserLogin

    fun getTokenUser(): String? = session.tokenUser

    fun onBoarding() {
        session.createOnBoardingSession()
    }

    fun isLogin() = session.isLogin

    fun logoutUser() = session.logout()

    fun isOnBoardingFinish() = session.onBoardingFinish
}