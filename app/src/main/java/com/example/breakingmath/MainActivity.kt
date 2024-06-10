package com.example.breakingmath

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var email: TextView = findViewById(R.id.email)
        var password: TextView = findViewById(R.id.password)
        var btn: ConstraintLayout = findViewById(R.id.btnSignIn)
        var sp = getSharedPreferences("TY", Context.MODE_PRIVATE)
        var db = Firebase.firestore
        btn.setOnClickListener {
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.getString("email")==email.text){
                            if (document.getString("pass")==password.text){
                                sp.edit().putString("Email", email.text.toString()).commit()
                                startActivity(Intent(this, MainActivity2::class.java))
                            }
                            else if (document.getString("pass")==password.text){
                                password.text = ""
                            }
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Попробуйте позже",Toast.LENGTH_LONG).show()
                }
            var sp = getSharedPreferences("TY", Context.MODE_PRIVATE)
        if (sp.getString("TY", "-9") != "-9") {
            startActivity(Intent(this, MainActivity2::class.java))}
            var signuptext: TextView = findViewById(R.id.tvSignIn)
            signuptext.setOnClickListener {
                var intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }
        }
    }
}