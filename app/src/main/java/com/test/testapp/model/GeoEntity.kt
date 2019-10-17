package com.test.testapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GeoEntity(

    @SerializedName("lat")
    @Expose
    val latitude : String,
    @SerializedName("lng")
    @Expose
    val longtitude : String
):Serializable