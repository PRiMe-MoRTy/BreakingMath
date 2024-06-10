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


class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var sp = getSharedPreferences("SP", Context.MODE_PRIVATE).edit()
        var email:TextView = findViewById(R.id.emailUp)
        var password:TextView = findViewById(R.id.passUp)
        var btn:ConstraintLayout = findViewById(R.id.btnSignUp)
        btn.setOnClickListener{
            if(email.text.isEmpty() || !email.text.contains("@")){
                Toast.makeText(this, "Ошибка, в поле email", Toast.LENGTH_LONG).show()
            } else if (password.text.isEmpty() || password.text.length < 8){
                Toast.makeText(this, "Пароль должен содержать больше 8 символов", Toast.LENGTH_LONG).show()
            } else {
                var db = Firebase.firestore

                val user = hashMapOf(
                    "email" to email.text.toString(),
                    "password" to password.text.toString()
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        sp.putString("Email", email.text.toString()).commit()
                        startActivity(Intent(this, MainActivity2::class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Попробуйте позже",Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}