package com.example.apps

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var savebutton: Button
    lateinit var nametextedit:EditText
    lateinit var value1:EditText
    lateinit var value2:EditText
    lateinit var displaytext:TextView

    //val database = Firebase.database

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     savebutton=findViewById(R.id.btnSve)
        nametextedit=findViewById(R.id.edName)
        value1=findViewById(R.id.edValue1)
        value2=findViewById(R.id.edValue2)
        displaytext=findViewById(R.id.tvHead)
        val database = Firebase.database
        var myRef = database.getReference("User")

        var user:User
  var isMale:String
        savebutton.setOnClickListener {
      user=User(value1.text.toString(),value2.text.isEmpty())
            myRef.setValue(user)
          //val grades:Map<String,Int> =mapOf("part1" to 87,"part2" to 80,"part3" to 100)
          //  myRef.setValue(nametextedit.text.toString())
          /*  myRef = database.getReference(nametextedit.text.toString())
            myRef.setValue(listOf( value1.text.toString(),value2.text.toString()))
           nametextedit.setText("")*/

        }
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
             isMale=if (value2.text.isEmpty())"Female" else "Male"
                  displaytext.text = snapshot.child("name").value.toString() + " is a " + isMale

                //to choose to dispkay
                //displaytext.text=snapshot.child("0").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

      /*  val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")*/

    }


}