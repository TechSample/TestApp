package com.test.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.testapp.repository.local.typeconverters.AddressConverter
import com.test.testapp.repository.local.typeconverters.CompanyConverter

@Entity
data class UserEntity(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id :Int,
    @SerializedName("name")
    @Expose
    val name :String,
    @SerializedName("username")
    @Expose
    val username : String,
    @SerializedName("email")
    @Expose
    val email : String,
    @SerializedName("address")
    @Expose
    @TypeConverters(AddressConverter::class)
    val address : AddressEntity,
    @SerializedName("phone")
    @Expose
    val phoneNumber : String,
    @SerializedName("website")
    @Expose
    val website : String,
    @SerializedName("company")
    @Expose
    @TypeConverters(CompanyConverter::class)
    val company : CompanyEntity,
    val isFavourite : Int = 0
)