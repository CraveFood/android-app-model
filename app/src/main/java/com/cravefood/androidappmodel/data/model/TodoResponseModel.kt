package com.cravefood.androidappmodel.data.model

import com.google.gson.annotations.SerializedName

data class TodoResponseModel(
    @SerializedName( "id") val id: Int?,
    @SerializedName( "userId") val userId: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName( "completed") val completed: Boolean?
)