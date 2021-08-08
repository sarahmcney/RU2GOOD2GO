package com.example.ru2good2go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.ru2good2go.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    /*private val viewModel: PlaceViewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }
    private lateinit var binding: ActivitySignUpBinding*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //setContentView(R.layout.activity_sign_up)
        //binding = ActivitySignUpBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel

        val fullNameEditText = findViewById<EditText>(R.id.fullNameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passEditText = findViewById<EditText>(R.id.passwordEditText)

        val testText = findViewById<TextView>(R.id.test)

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val signInLink = findViewById<TextView>(R.id.linkToSignIn)
        //val signUpButton: Button = binding.signUpButton
        //val signInLink: TextView = binding.linkToSignIn

        signInLink.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            /*var fullNameInput = fullNameEditText.text.toString()
            var emailInput = emailEditText.text.toString()
            var passInput = passEditText.text.toString()

            testText.text = fullNameInput + ", " + emailInput + ", " + passInput
            testText.visibility = View.VISIBLE*/
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
        }

    }
}
