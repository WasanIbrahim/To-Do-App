package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter (val messages: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    val selectedTodo = arrayListOf<Int>()
    var bool: Boolean = false

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false //don't attach to root
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val myText = messages[position] //access to each item in list
        holder.itemView.apply {
            //changing each view
            toDotxt.text = myText
            if(!bool)
                myCheckBox.isChecked = false

            myCheckBox.setOnClickListener {
                bool = true
                if(myCheckBox.isChecked)
                    selectedTodo.add(position)
                else
                    selectedTodo.remove(position)
            }

        }
    }

    override fun getItemCount() = messages.size //return the count of the list
}