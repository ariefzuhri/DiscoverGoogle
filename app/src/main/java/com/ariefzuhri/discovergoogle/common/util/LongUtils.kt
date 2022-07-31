package com.ariefzuhri.discovergoogle.common.util

fun Long?.orZero(): Long {
    return this ?: 0
}