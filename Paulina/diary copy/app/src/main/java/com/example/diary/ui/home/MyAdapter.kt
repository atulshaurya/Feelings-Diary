package com.example.diary.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.ExampleItem
import com.example.diary.R

class MyAdapter(private val exampleList: List<ExampleItem>) :
    RecyclerView.Adapter<MyAdapter.ExampleViewHolder>() {
    val list_limit = 10


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val imageView1: ImageView = itemView.findViewById(R.id.imageView4)

        val imageView2: ImageView = itemView.findViewById(R.id.imageView9)

        val textView1: TextView = itemView.findViewById(R.id.text_view_1)

        val textView2: TextView = itemView.findViewById(R.id.text_view_2)

        init {
            itemView.setOnClickListener{
                val mContext: Context? = null
                val mData: List<ExampleItem>? = null
                val position = adapterPosition
                val intent = Intent(mContext, CommentSection::class.java)

                intent.putExtra("userImg", mData?.get(position)?.avatar)
                intent.putExtra("emoji", mData?.get(position)?.emoji)
                intent.putExtra("user", mData?.get(position)?.username)
                intent.putExtra("feelings", mData?.get(position)?.feelings)

                mContext?.startActivity(intent)

            }
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_row,

            parent, false)

        return ExampleViewHolder(itemView)

    }



    override fun getItemCount(): Int {
        return if(exampleList.size > list_limit){
            list_limit;
        } else {
            exampleList.size
        }
    }



    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {

        val currentItem = exampleList[position]


        if(currentItem.avatar!=null){
            holder.imageView1.setImageResource(currentItem.avatar!!)
        }

        if(currentItem.emoji!=null){
            holder.imageView2.setImageResource(currentItem.emoji!!)
        }

        holder.textView1.text = currentItem.username

        holder.textView2.text = currentItem.feelings

    }


}