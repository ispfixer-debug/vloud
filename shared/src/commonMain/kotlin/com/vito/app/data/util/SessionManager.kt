package com.vito.app.data.util

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoAdmin

class SessionManager private constructor() {
    companion object {
        @Volatile
        private var instance: SessionManager? = null
        
        fun getInstance(): SessionManager {
            return instance ?: synchronized(this) {
                instance ?: SessionManager().also { instance = it }
            }
        }
    }
    
    var currentUser: VitoUser? = null
    var currentDriver: VitoDriver? = null
    var currentAdmin: VitoAdmin? = null
    var sessionType: SessionType = SessionType.NONE
    
    enum class SessionType {
        NONE,
        USER,
        DRIVER,
        ADMIN
    }
    
    fun isLoggedIn(): Boolean = sessionType != SessionType.NONE
    
    fun clearSession() {
        currentUser = null
        currentDriver = null
        currentAdmin = null
        sessionType = SessionType.NONE
    }
}