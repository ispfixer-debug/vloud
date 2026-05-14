package com.vito.app.global.util

// Simple logger implementation using println
object VitoLogger {
    private const val TAG = "Vito"
    
    fun d(message: String, tag: String = TAG) {
        println("[$tag] DEBUG: $message")
    }
    
    fun e(message: String, tag: String = TAG) {
        println("[$tag] ERROR: $message")
    }
    
    fun w(message: String, tag: String = TAG) {
        println("[$tag] WARN: $message")
    }
    
    fun i(message: String, tag: String = TAG) {
        println("[$tag] INFO: $message")
    }
}

fun logger(tag: String = "", message: String) {
    VitoLogger.d(message, tag = "==> $tag")
}

fun loggerError(tag: String = "", error: Throwable) {
    VitoLogger.e(error.stackTraceToString(), tag = "==> $tag")
}