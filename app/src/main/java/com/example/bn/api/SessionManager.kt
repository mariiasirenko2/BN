package com.example.bn.api

import com.example.bn.dto.ClientProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SessionManager private constructor() {
    private var token: String = ""
    private var userApi: ApiInterface = ApiUtils.getInstance().create(ApiInterface::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var userData: ClientProfile = ClientProfile()

    companion object {
        private var instance: SessionManager? = null

        fun getInstance(): SessionManager {
            if (instance == null) {
                instance = SessionManager()
            }
            return instance as SessionManager
        }
    }
    fun setUser(user: ClientProfile) {
        userData = user
    }

    fun getUser(): ClientProfile {
        return userData
    }
    fun setToken(jwtToken: String) {
        token = jwtToken
    }

    fun getToken(): String {
        return token
    }

    fun getUserApi(): ApiInterface {
        return userApi
    }

    fun getCoroutineScope(): CoroutineScope {
        return coroutineScope
    }
}
