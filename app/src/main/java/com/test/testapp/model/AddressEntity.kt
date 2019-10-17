package com.test.testapp.model

import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.testapp.repository.local.typeconverters.GeoConverter
import java.io.Serializable

data class AddressEntity(

    @SerializedName("street")
    @Expose
    val street : String,
    @SerializedName("suite")
    @Expose
    val suite : String,
    @SerializedName("city")
    @Expose
    val city : String,
    @SerializedName("zipcode")
    @Expose
    val zipcode : String,
    @SerializedName("geo")
    @Expose
    @TypeConverters(GeoConverter::class)
    val geo : GeoEntity
): Serializable