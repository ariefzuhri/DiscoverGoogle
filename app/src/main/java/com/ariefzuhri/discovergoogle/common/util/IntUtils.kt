package com.ariefzuhri.discovergoogle.common.util

fun Int?.orZero(): Int {
    return this ?: 0
}