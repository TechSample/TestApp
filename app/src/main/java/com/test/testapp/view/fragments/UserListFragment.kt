package com.test.testapp.view.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
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
import com.test.testapp.view.activities.UserDetailActivity
import com.test.testapp.view.adapters.UserListAdapter
import kotlinx.android.synthetic.main.frag_userlist.*

class UserListFragment : Fragment() , UserListAdapter.UserListListener{


    var mAdapter : UserListAdapter ?= null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return  inflater!!.inflate(R.layout.frag_userlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(isNetworkConnected(activity!!)){
            rvUsersList.visibility = View.GONE
            shimmer_view_container.visibility = View.VISIBLE
            shimmer_view_container.startShimmerAnimation()

            (this.activity as DashboardActivity).mViewModel.getUsersFromAPIAndStore()
        } else
        {
            //  Toast.makeText(this,"No internet found. Showing cached list in the view", Toast.LENGTH_LONG).show()
        }

        (this.activity as DashboardActivity).mViewModel.getAllUsersList().observe(this, Observer<List<UserEntity>> { usersList ->

            if(mAdapter == null){

                mAdapter = UserListAdapter(activity!!, usersList, this)
                rvUsersList.adapter = mAdapter
                rvUsersList.layoutManager = LinearLayoutManager(activity!!)
                shimmer_view_container.visibility = View.GONE
                shimmer_view_container.stopShimmerAnimation()
                rvUsersList.visibility = View.VISIBLE

            }else{

                mAdapter!!.userList = usersList
                mAdapter!!.notifyDataSetChanged()
            }

         })


    }


    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onFavClick(user: UserEntity) {
        var isFav = 0
        if(user.isFavourite == 0){
            isFav = 1
        }
        (this.activity as DashboardActivity).mViewModel.updateUser(isFav,user.id)
    }

    override fun onItemClick(user: UserEntity) {

        val intent = Intent(activity!!, UserDetailActivity::class.java)
        intent.putExtra("address",user.address)

        startActivity(intent)
    }

}