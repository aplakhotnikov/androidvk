package com.example.androidvk

import android.content.Context
import android.content.Intent

internal fun isPhoneNumber(text: String): Boolean {
    val phoneRegex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?\\d{3}[\\- ]?\\d{2}[\\- ]?\\d{2}$".toRegex();

    return phoneRegex.matches(text);
}

internal fun isActivityResolved(context: Context, intent: Intent): Boolean {
    return intent.resolveActivity(context.packageManager) != null;
}