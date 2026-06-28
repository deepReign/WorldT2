package com.flipkart.machinecoding.worldt2.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
//    @SerializedName("name")
    @SerialName("name")
    val name: String,

//    @SerializedName("flag")
    @SerialName("flag")
    val flag: String
)