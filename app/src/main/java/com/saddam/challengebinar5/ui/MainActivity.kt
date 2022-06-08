package com.saddam.challengebinar5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saddam.challengebinar5.R
import com.saddam.challengebinar5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}