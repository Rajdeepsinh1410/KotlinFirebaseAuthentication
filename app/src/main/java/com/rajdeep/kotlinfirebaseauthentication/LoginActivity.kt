package com.rajdeep.kotlinfirebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var LoginBtn : Button
    private lateinit var emailText : EditText
    private lateinit var passwordText : EditText
    private lateinit var signUpText : TextView
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        LoginBtn = findViewById(R.id.loginBtn)
        emailText  =  findViewById(R.id.loginemail)
        passwordText = findViewById(R.id.loginpassword)
        signUpText = findViewById(R.id.signUpText)
        firebaseAuth = FirebaseAuth.getInstance()
        LoginBtn.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Login Successful!!", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,"Login Failed!!", Toast.LENGTH_LONG).show()
                        }
                    }
            } else{
                Toast.makeText(this,"Enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
        signUpText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}