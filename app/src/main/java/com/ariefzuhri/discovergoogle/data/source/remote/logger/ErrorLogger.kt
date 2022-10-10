package com.ariefzuhri.discovergoogle.data.source.remote.logger

interface ErrorLogger {

    fun e(tag: String, message: String?): Exception

    fun e(tag: String, t: Throwable?): Exception
}