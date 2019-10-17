package com.test.testapp.repository.local.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.testapp.model.CompanyEntity

class CompanyConverter {

    @TypeConverter
    fun stringToCompanyEntity(json: String): CompanyEntity? {
        val gson = Gson()
        val type = object : TypeToken<CompanyEntity?>() {

        }.type
        return gson.fromJson<CompanyEntity?>(json, type)
    }

    @TypeConverter
    fun companyToString(basicTabEntity: CompanyEntity?): String {
        val gson = Gson()
        val type = object : TypeToken<CompanyEntity?>() {

        }.getType()
        return gson.toJson(basicTabEntity, type)
    }


}