package com.example.harrypotter.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ActivityDetailsBinding
import com.example.harrypotter.model.StudentDetailHP
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

       // Log.d(Constants.LOGTAG, "Respuesta del call: $call")

        call.enqueue(object : Callback<ArrayList<StudentDetailHP>> {
            override fun onResponse(
                call: Call<ArrayList<StudentDetailHP>>,
                response: Response<ArrayList<StudentDetailHP>>
            ) {
                val personaje = response.body()!![0].name
                val casa = response.body()!![0].house
                val fechaDeNacimiento = response.body()!![0].dateOfBirth
                val ascendencia = response.body()!![0].ancestry
                val patronus = response.body()!![0].patronus
                val actor = response.body()!![0].actor
                val empty = getString(R.string.EMPTY)

                binding.pbConexion.visibility = View.GONE

                binding.tvCharacterName.text = if (!personaje.isNullOrBlank()) "$personaje" else empty

                binding.tvHouse.text = if (!casa.isNullOrBlank()) "${getString(R.string.house)}: $casa" else "${getString(R.string.house)}: $empty"
                binding.tvDateOfBirth.text = if (!fechaDeNacimiento.isNullOrBlank()) "${getString(R.string.dateOfBirth)}: $fechaDeNacimiento" else "${getString(R.string.dateOfBirth)}: $empty"
                binding.tvAncestry.text = if (!ascendencia.isNullOrBlank()) "${getString(R.string.ancestry)}: $ascendencia" else "${getString(R.string.ancestry)}: $empty"
                binding.tvPatronus.text = if (!patronus.isNullOrBlank()) "${getString(R.string.patronus)}: $patronus" else "${getString(R.string.patronus)}: $empty"
                binding.tvActorName.text = if (!actor.isNullOrBlank()) "${getString(R.string.actor)}: $actor" else "${getString(R.string.actor)}: $empty"

                if (!response.body()!![0].image?.isEmpty()!!) {
                    Glide.with(this@Details)
                        .load(response.body()!![0].image)
                        .into(binding.ivImage)
                } else {
                   binding.ivImage.setImageResource(R.drawable.character)
                }
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