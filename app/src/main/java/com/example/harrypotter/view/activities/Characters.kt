package com.example.harrypotter.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotter.databinding.ActivityCharactersBinding

class Characters : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val seleccion = bundle?.getString("seleccion", "")

        /*Toast.makeText(this, "Seleccion ${seleccion}", Toast.LENGTH_SHORT).show()*/





    }
}