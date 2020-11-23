package com.kc.todoapp.util.network

import org.json.JSONObject
import retrofit2.HttpException

object ErrorHandler {

    fun getApiErrorMessage(responce: Throwable): String {
        try {
            val jsonObject =
                JSONObject(String((responce as HttpException).response().errorBody()?.bytes()!!))
            return jsonObject.getString("message")
        } catch (ex: Exception) {
            return "Oops, something went wrong. Let\'s try it again."
        }
    }
}
