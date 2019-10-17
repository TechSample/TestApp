package com.test.testapp.view.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.test.testapp.R
import com.test.testapp.model.UserEntity
import com.test.testapp.view.DashboardViewPager
import com.test.testapp.view.adapters.UserListAdapter
import com.test.testapp.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(){


    lateinit var mViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        mViewModel = ViewModelProviders.of(this, DashboardViewModel.Factory(this)).get(DashboardViewModel::class.java)

        tabLayout!!.addTab(tabLayout!!.newTab().setText(getString(R.string.tab_userlist)))
        tabLayout!!.addTab(tabLayout!!.newTab().setText(getString(R.string.tab_favlist)))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = DashboardViewPager(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }


}