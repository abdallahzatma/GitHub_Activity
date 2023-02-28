package com.example.helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
val db = Firebase.firestore
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            var name = name.text.toString()
            var age = age.text.toString()
            var id = id.text.toString()

            if(name.isEmpty() || id.isEmpty() || age.isEmpty() ){
                Toast.makeText(this, "The field is empty add data in field", Toast.LENGTH_SHORT).show()


            }else{

                val person = hashMapOf(
                    "id" to "$id",
                    "name" to "$name",
                    "age" to "$age"

                )
                db.collection("user").add(person).addOnSuccessListener {e ->

                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()

                    this.id.text.clear()
                    this.age.text.clear()
                    this.name.text.clear()

                }.addOnFailureListener {
                        e->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                }
            }

        }


    }
}