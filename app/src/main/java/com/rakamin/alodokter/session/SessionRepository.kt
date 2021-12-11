package com.rakamin.alodokter.session

class SessionRepository(private val session: SessionManager) {

    fun loginUser(idUser: Int) {
        session.createLoginSession()
        session.savToPreferences(SessionManager.KEY_ID_USER, idUser)
    }

    fun getIdUser(): Int {
        return session.idUserLogin
    }

    fun onBoarding() {
        session.createOnBoardingSession()
    }

    fun isLogin() = session.isLogin

    fun logoutUser() = session.logout()

    fun isOnBoardingFinish() = session.onBoardingFinish
}