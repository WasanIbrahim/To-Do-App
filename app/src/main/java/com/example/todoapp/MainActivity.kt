package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var messages = ArrayList<String>()

    lateinit var myRV: RecyclerView
    lateinit var rvAdapter: RecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById(R.id.mainRV)
        myRV.adapter = RecyclerViewAdapter(messages)
        myRV.layoutManager = LinearLayoutManager(this) // main activity

        var addButton = findViewById<FloatingActionButton>(R.id.addButton)


        //if button pressed , show alert to ask user to enter message then store it in the toDoText
        addButton.setOnClickListener {
            //alert
            Log.d("MAIN", messages.toString())
            customAlert()
            myRV.adapter?.notifyDataSetChanged()

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.trash, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        rvAdapter.selectedTodo.sort()
        for(i in rvAdapter.selectedTodo.reversed()){
            messages.removeAt(i)
        }
        rvAdapter.bool = false
        rvAdapter.selectedTodo.clear()
        rvAdapter.notifyDataSetChanged()

        return super.onOptionsItemSelected(item)
    }

    private fun customAlert() {
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Enter your Item:")
        // positive button text and action
        dialogBuilder.setPositiveButton("Submit", DialogInterface.OnClickListener {

                dialog, id ->
            val text = input.text.toString()
            if (text.isNotEmpty())
                messages.add(text)

        })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("New Item")
        // add the Edit Text

        alert.setView(input)
        // show alert dialog
        alert.show()
        rvAdapter = RecyclerViewAdapter(messages)
        myRV.adapter = rvAdapter
        myRV.layoutManager = LinearLayoutManager(this)
    }

}