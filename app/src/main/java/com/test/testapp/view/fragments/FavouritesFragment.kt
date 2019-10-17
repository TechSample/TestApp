package com.test.testapp.view.fragments

import android.icu.util.ValueIterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testapp.R
import com.test.testapp.model.UserEntity
import com.test.testapp.view.activities.DashboardActivity
import com.test.testapp.view.adapters.FavListAdapter
import com.test.testapp.view.adapters.UserListAdapter
import kotlinx.android.synthetic.main.frag_fav.*
import kotlinx.android.synthetic.main.frag_userlist.*

class FavouritesFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater!!.inflate(R.layout.frag_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (this.activity as DashboardActivity).mViewModel.getAllFavUsersList().observe(this, Observer<List<UserEntity>> { usersList ->
          // Toast.makeText(activity!!,"Fav - "+usersList.size, Toast.LENGTH_SHORT).show()
            if(usersList.size == 0){
                txtNoFav.visibility = View.VISIBLE
                rvFavUsersList.visibility = View.GONE
            }else{
                txtNoFav.visibility = View.GONE
                rvFavUsersList.visibility = View.VISIBLE
                setUpRecyclerView(usersList)
            }
        })
    }

    fun setUpRecyclerView(users : List<UserEntity>)
    {
        val mAdapter = FavListAdapter(activity!!, users)
        rvFavUsersList.adapter = mAdapter
        rvFavUsersList.layoutManager = LinearLayoutManager(activity!!)


    }
}