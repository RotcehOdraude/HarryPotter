package com.example.harrypotter.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.harrypotter.databinding.ActivityDetailsBinding
import com.example.harrypotter.model.StudentDetailHP
import com.example.harrypotter.model.StudentHP
import com.example.harrypotter.network.HPApi
import com.example.harrypotter.network.RetrofitService
import com.example.harrypotter.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "")
        val call = RetrofitService.getRetrofit().create(HPApi::class.java)
            .getStudentDetail("/api/character/$id")

        Log.d(Constants.LOGTAG, "Respuesta del call: ${call}")

        call.enqueue(object : Callback<ArrayList<StudentDetailHP>> {
            override fun onResponse(
                call: Call<ArrayList<StudentDetailHP>>,
                response: Response<ArrayList<StudentDetailHP>>
            ) {
                binding.pbConexion.visibility = View.GONE
                binding.tvTitle.text = response.body()!![0].name
                binding.tvLongDesc.text = response.body()!![0].actor

                Glide.with(this@Details)
                    .load(response.body()!![0].image)
                    .into(binding.ivImage)
            }

            override fun onFailure(
                call: Call<ArrayList<StudentDetailHP>>,
                t: Throwable
            ) {
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@Details, "No hay conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}