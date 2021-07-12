package com.example.ru2good2go

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.text)
        text.visibility = View.INVISIBLE

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            text.visibility = View.VISIBLE
        }
    }
}
