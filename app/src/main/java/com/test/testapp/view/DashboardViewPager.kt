package com.test.testapp.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.testapp.view.adapters.UserListAdapter
import com.test.testapp.view.fragments.FavouritesFragment
import com.test.testapp.view.fragments.UserListFragment

class DashboardViewPager(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return UserListFragment()
            }
            1 -> {
                return FavouritesFragment()
            }
            else -> return UserListFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}