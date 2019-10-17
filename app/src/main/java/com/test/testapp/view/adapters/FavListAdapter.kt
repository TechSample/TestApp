package com.test.testapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.model.UserEntity

class FavListAdapter(context : Context, userList:List<UserEntity>) : RecyclerView.Adapter<FavListAdapter.UserViewHolder>() {

    val context = context
    val userList = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.row_fav_list,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder:UserViewHolder, position: Int) {
        val user = userList[position]
        holder.userId.text = user.id.toString()
        holder.userName.text = "Name - "+user.name
        holder.userUName.text = "UserName - "+user.username
        holder.userEmail.text ="Email - " +user.email
        holder.userCompany.text = "Company - "+user.company.name

    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val userId : TextView = itemView.findViewById(R.id.txtUserid)
        val userName : TextView = itemView.findViewById(R.id.txtName)
        val userUName : TextView = itemView.findViewById(R.id.txtUserName)
        val userEmail : TextView = itemView.findViewById(R.id.txtEmail)
        val userCompany : TextView = itemView.findViewById(R.id.txtCompany)
        val imgFav : ImageView = itemView.findViewById(R.id.imgFav)
    }


    interface UserListListener {

        fun onFavClick(user : UserEntity)
    }

}