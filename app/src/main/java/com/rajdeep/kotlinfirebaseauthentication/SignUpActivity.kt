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

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBtn : Button
    private lateinit var emailText : EditText
    private lateinit var passwordText : EditText
    private lateinit var loginText : TextView
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        signUpBtn = findViewById(R.id.signUpBtn)
        emailText = findViewById(R.id.signUpemail)
        passwordText = findViewById(R.id.signUppassword)
        loginText = findViewById(R.id.loginText)
        firebaseAuth = FirebaseAuth.getInstance()
        signUpBtn.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"SignUp SuccessFul!!!",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                    }
                }
            } else{
                Toast.makeText(this,"Enter the email and password", Toast.LENGTH_SHORT).show()
            }
        }
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}