package com.example.diary.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R

class CommentAdapter(private val mContext: Context, private val mData: List<Comment>) :
        RecyclerView.Adapter<CommentAdapter.CommentViewHolder?>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_user: ImageView = itemView.findViewById(R.id.comment_user_img)
        var tv_name: TextView = itemView.findViewById(R.id.comment_username)
        var tv_content: TextView = itemView.findViewById(R.id.comment_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.row_comment, parent, false)
        return CommentViewHolder(row)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentItem = mData[position]
        if(currentItem.imageResource != null) {
            holder.img_user.setImageResource(currentItem.imageResource!!)
        }
        holder.tv_name.text = mData[position].getUname()
        holder.tv_content.text = mData[position].getContent()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}