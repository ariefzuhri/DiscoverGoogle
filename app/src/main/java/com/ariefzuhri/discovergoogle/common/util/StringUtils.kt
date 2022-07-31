package com.ariefzuhri.discovergoogle.common.util

fun String?.getHostName(): String {
    return this?.split("/")?.get(2).orEmpty()
}