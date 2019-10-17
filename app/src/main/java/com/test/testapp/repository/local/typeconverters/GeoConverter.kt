package com.test.testapp.repository.local.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.testapp.model.AddressEntity
import com.test.testapp.model.GeoEntity

class GeoConverter {

    @TypeConverter
    fun stringToGeoEntity(json: String): GeoEntity? {
        val gson = Gson()
        val type = object : TypeToken<AddressEntity?>() {

        }.type
        return gson.fromJson<GeoEntity?>(json, type)
    }

    @TypeConverter
    fun geoToString(geoEntity: GeoEntity?): String {
        val gson = Gson()
        val type = object : TypeToken<GeoEntity?>() {

        }.getType()
        return gson.toJson(geoEntity, type)
    }


}