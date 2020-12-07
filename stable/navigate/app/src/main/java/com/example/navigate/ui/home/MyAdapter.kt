package com.example.navigate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigate.ExampleItem
import com.example.navigate.R

/* The adapter class for the public feed to be able to show each element of the post such as the username,
* the avatar, the feeling and the post */

class MyAdapter(private val exampleList: List<ExampleItem>,
                private var listener: ItemClickListener) :
    RecyclerView.Adapter<MyAdapter.ExampleViewHolder>() {
    val list_limit = 10

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView1 : ImageView
        val imageView2 : ImageView
        val textView1 : TextView
        val textView2 : TextView

        init {
            imageView1 = itemView.findViewById(R.id.imageView4)
            imageView2 = itemView.findViewById(R.id.imageView9)
            textView1= itemView.findViewById(R.id.text_view_1)
            textView2 = itemView.findViewById(R.id.text_view_2)

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onClick(adapterPosition)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_row,
            parent, false)
        return ExampleViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return exampleList.size
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