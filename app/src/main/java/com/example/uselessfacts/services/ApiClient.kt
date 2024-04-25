package com.example.uselessfacts.services

import com.example.uselessfacts.model.ResponseModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object FactsAPI {
    private val client = OkHttpClient()

    fun sendGet(lang:String = "en",option:String = "random"): ResponseModel {
        val request = Request.Builder().url("https://uselessfacts.jsph.pl/api/v2/facts/$option?language=$lang").build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            val response = it.body.string()
            val gson = Gson()
            gson.fromJson(response,ResponseModel::class.java)
        }
    }
}