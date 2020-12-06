package com.example.navigate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.ExampleItem
import com.example.navigate.R


class MyAdapter(private val exampleList: List<ExampleItem>) :
    RecyclerView.Adapter<MyAdapter.ExampleViewHolder>() {
    val list_limit = 10

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView1: ImageView = itemView.findViewById(R.id.imageView4)
        val imageView2: ImageView = itemView.findViewById(R.id.imageView9)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
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
            holder.imageView1.setImageResource(currentItem.avatar!!.toInt())
        }

        if(currentItem.emoji!=null){
            holder.imageView2.setImageResource(currentItem.emoji!!.toInt())
        }

        holder.textView1.text = currentItem.username
        holder.textView2.text = currentItem.feelings
        holder.textView1.text = currentItem.username

    }

}