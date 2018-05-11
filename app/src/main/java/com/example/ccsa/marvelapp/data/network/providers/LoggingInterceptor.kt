package com.example.ccsa.marvelapp.data.network.providers

import com.example.ccsa.marvelapp.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}