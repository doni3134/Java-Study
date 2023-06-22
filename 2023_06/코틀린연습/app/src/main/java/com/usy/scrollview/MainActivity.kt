package com.usy.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.wear.tiles.material.Button
//import com.google.firebase.inappmessaging.model.Button
import com.usy.scrollview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener{(startActivity(intent))}
        binding.button2.setOnClickListener{(startActivity(intent))}
        binding.button3.setOnClickListener{(startActivity(intent))}
        binding.button4.setOnClickListener{(startActivity(intent))}



    }
}