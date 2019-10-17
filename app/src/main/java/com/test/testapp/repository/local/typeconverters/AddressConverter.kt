package com.test.testapp.repository.local.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.testapp.model.AddressEntity

class AddressConverter {

    @TypeConverter
    fun stringToAddressEntity(json: String): AddressEntity? {
        val gson = Gson()
        val type = object : TypeToken<AddressEntity?>() {

        }.type
        return gson.fromJson<AddressEntity?>(json, type)
    }

    @TypeConverter
    fun addressToString(basicTabEntity:AddressEntity?): String {
        val gson = Gson()
        val type = object : TypeToken<AddressEntity?>() {

        }.getType()
        return gson.toJson(basicTabEntity, type)
    }


}