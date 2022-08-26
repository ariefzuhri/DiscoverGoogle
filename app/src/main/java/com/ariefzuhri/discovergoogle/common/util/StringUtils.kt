package com.ariefzuhri.discovergoogle.common.util

fun emptyString(): String {
    return ""
}

fun String?.getHostName(): String {
    return this?.split("/")?.get(2).orEmpty()
}