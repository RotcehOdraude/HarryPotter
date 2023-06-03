package com.example.harrypotter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.harrypotter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun onClickStaff(view: View) {
        val intent = Intent(this, Characters::class.java)
        startActivity(intent)
    }
    fun onClickStudent(view: View) {
        val intent = Intent(this, Characters::class.java)
        startActivity(intent)
    }

}