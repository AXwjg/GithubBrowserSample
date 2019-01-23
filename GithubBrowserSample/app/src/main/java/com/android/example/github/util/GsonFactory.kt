package com.android.example.github.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer

import java.util.Date

object GsonFactory {

    fun create(): Gson {
        val builder = GsonBuilder()
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date::class.java, JsonDeserializer {
            json, _, _ -> Date(json.asJsonPrimitive.asLong) })

        return builder.setLenient().create()
    }
}

