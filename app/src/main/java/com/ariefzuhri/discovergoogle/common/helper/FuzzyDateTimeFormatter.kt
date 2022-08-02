/**
 * FuzzyDateFormatter by Jernej Virag (github.com/izacus)
 */

package com.ariefzuhri.discovergoogle.common.helper

import android.content.Context
import android.util.Log
import com.ariefzuhri.discovergoogle.R
import com.ariefzuhri.discovergoogle.common.util.orZero
import java.util.*

object FuzzyDateTimeFormatter {

    private val TAG = FuzzyDateTimeFormatter::class.java.simpleName

    private const val SECONDS = 1
    private const val MINUTES = 60 * SECONDS
    private const val HOURS = 60 * MINUTES
    private const val DAYS = 24 * HOURS
    private const val WEEKS = 7 * DAYS
    private const val MONTHS = 4 * WEEKS
    private const val YEARS = 12 * MONTHS

    /**
     * Returns a properly formatted fuzzy string representing time ago
     * @param context   Context
     * @param date      Absolute date of the event
     * @return          Formatted string
     */
    fun getTimeAgo(context: Context?, date: Date?): String {
        val beforeSeconds = (date?.time.orZero() / 1000).toInt()
        val nowSeconds = (Calendar.getInstance().timeInMillis / 1000).toInt()
        val timeDifference = nowSeconds - beforeSeconds

        if (timeDifference < 0) {
            with(date.toString()) {
                Log.e(TAG, "Date $this must be in the past!")
                return this
            }
        }

        context?.resources?.let { res ->
            return when {
                timeDifference < 15 * SECONDS -> {
                    res.getString(R.string.fuzzydatetime_now)
                }
                timeDifference < MINUTES -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_seconds_ago,
                        timeDifference,
                        timeDifference)
                }
                timeDifference < HOURS -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_minutes_ago,
                        timeDifference / MINUTES,
                        timeDifference / MINUTES)
                }
                timeDifference < DAYS -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_hours_ago,
                        timeDifference / HOURS,
                        timeDifference / HOURS)
                }
                timeDifference < WEEKS -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_days_ago,
                        timeDifference / DAYS,
                        timeDifference / DAYS)
                }
                timeDifference < MONTHS -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_weeks_ago,
                        timeDifference / WEEKS,
                        timeDifference / WEEKS)
                }
                timeDifference < YEARS -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_months_ago,
                        timeDifference / MONTHS,
                        timeDifference / MONTHS)
                }
                else -> {
                    res.getQuantityString(R.plurals.fuzzydatetime_years_ago,
                        timeDifference / YEARS,
                        timeDifference / YEARS)
                }
            }
        } ?: run {
            Log.e(TAG, "Context is null")
            return date.toString()
        }
    }
}