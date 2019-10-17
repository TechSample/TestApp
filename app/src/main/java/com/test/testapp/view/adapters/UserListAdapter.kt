package com.test.testapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.model.UserEntity

class UserListAdapter(context : Context, userList:List<UserEntity>, userListListener: UserListListener) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    val context = context
    var userList = userList
    val mListener = userListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.row_user_list,parent,false)
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

        if(user.isFavourite == 0){
            holder.imgFav.setImageResource(R.drawable.ic_fav_disable)
        }else{
            holder.imgFav.setImageResource(R.drawable.ic_fav_enable)
        }

        holder.imgFav.setOnClickListener {

            if(mListener != null){
                mListener.onFavClick(user)
                //holder.imgFav.setImageResource(R.drawable.ic_fav_enable)
            }
        }

        holder.container.setOnClickListener {

            if(mListener != null){
                mListener.onItemClick(user)
            }
        }

    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val userId : TextView = itemView.findViewById(R.id.txtUserid)
        val userName : TextView = itemView.findViewById(R.id.txtName)
        val userUName : TextView = itemView.findViewById(R.id.txtUserName)
        val userEmail : TextView = itemView.findViewById(R.id.txtEmail)
        val userCompany : TextView = itemView.findViewById(R.id.txtCompany)
        val imgFav : ImageView = itemView.findViewById(R.id.imgFav)
        val container : ConstraintLayout = itemView.findViewById(R.id.container)
    }


    interface UserListListener {

        fun onFavClick(user : UserEntity)

        fun onItemClick(user : UserEntity)
    }

}