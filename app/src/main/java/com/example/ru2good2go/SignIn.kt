package com.example.ru2good2go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.ru2good2go.databinding.ActivityMainBinding

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passEditText = findViewById<EditText>(R.id.passwordEditText)

        val testText = findViewById<TextView>(R.id.test)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpLink = findViewById<TextView>(R.id.linkToSignup)

        signUpLink.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            /*var emailInput = emailEditText.text.toString()
            var passInput = passEditText.text.toString()

            testText.text = emailInput + ", " + passInput
            testText.visibility = View.VISIBLE*/
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
        }
    }
}
