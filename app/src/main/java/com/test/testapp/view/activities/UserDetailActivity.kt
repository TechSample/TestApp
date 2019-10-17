package com.test.testapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.testapp.R
import com.test.testapp.model.AddressEntity
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var mAddressEntity : AddressEntity ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setTitle("User Detail")

        if(intent.getSerializableExtra("address")!= null){
           mAddressEntity = intent.getSerializableExtra("address") as AddressEntity
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location = LatLng(mAddressEntity!!.geo.latitude!!.toDouble(),
            mAddressEntity!!.geo.longtitude!!.toDouble())
        val address =
            StringBuilder("Address: ")
                .append(mAddressEntity!!.suite).append(", ")
                .append(mAddressEntity!!.street).append(mAddressEntity!!.city).append(", ")
                .append(mAddressEntity!!.zipcode)
        txtUserAddress.text = address.toString()
        mMap.addMarker(MarkerOptions().position(location).title(""))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}
