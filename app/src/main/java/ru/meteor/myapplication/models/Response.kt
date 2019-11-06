package ru.meteor.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response<T> (
    @SerializedName("response")
    @Expose
    val response: T
)