package com.example.navigate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.R

/* This comment adapter will allow us to create a view of comments by using the recycler view

* The comment adapter was referenced by this youtube video: https://www.youtube.com/watch?v=HEJg-hvj0nE&t=131s&ab_channel=AwsRh */

class CommentAdapter(private val mData: List<Comment>) :
        RecyclerView.Adapter<CommentAdapter.CommentViewHolder?>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username: TextView = itemView.findViewById(R.id.comment_username)
        var content: TextView = itemView.findViewById(R.id.comment_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false)
        return CommentViewHolder(row)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentItem = mData[position]

        holder.username.text = currentItem.uname
        holder.content.text = currentItem.comment_content
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}
