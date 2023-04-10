package ca.josue.homefinder.domain.models

import com.google.gson.annotations.SerializedName

data class Authentication(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
)
