package com.example.diary.ui.home

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



    override fun getItemCount() = exampleList.size



    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {

        val currentItem = exampleList[position]


        if(currentItem.imageResource!=null){
            holder.imageView1.setImageResource(currentItem.imageResource!!)
        }

        if(currentItem.imageResource2!=null){
            holder.imageView2.setImageResource(currentItem.imageResource2!!)
        }

        holder.textView1.text = currentItem.text1

        holder.textView2.text = currentItem.text2

    }

}