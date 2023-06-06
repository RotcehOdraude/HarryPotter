package com.example.harrypotter.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.databinding.ActivityCharactersBinding
import com.example.harrypotter.model.StudentHP
import com.example.harrypotter.network.HPApi
import com.example.harrypotter.network.RetrofitService
import com.example.harrypotter.utils.Constants
import com.example.harrypotter.view.adapters.StudentsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Characters : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val seleccion = bundle?.getString("seleccion", "")

        Log.d(Constants.LOGTAG, "Respuesta del bundle: ${seleccion}")

        if (seleccion.equals("Student")) {
            //Toast.makeText(this, "Seleccion ${seleccion}", Toast.LENGTH_SHORT).show()

            val call = RetrofitService.getRetrofit().create(HPApi::class.java)
                .getStudents("api/characters")
            Log.d(Constants.LOGTAG, "Respuesta del call: ${call}")

            call.enqueue(object : Callback<ArrayList<StudentHP>> {
                override fun onResponse(
                    call: Call<ArrayList<StudentHP>>,
                    response: Response<ArrayList<StudentHP>>
                ) {
                    binding.pbConexion.visibility = View.GONE

                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")

                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@Characters)
                    binding.rvMenu.adapter = StudentsAdapter(
                        this@Characters,
                        response.body()!!
                    ) { selectedGame: StudentHP ->
                        gameClicked(selectedGame)
                    }

                }

                override fun onFailure(call: Call<ArrayList<StudentHP>>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Toast.makeText(this@Characters, "No hay conexión", Toast.LENGTH_SHORT).show()
                }

            })
        } else if (seleccion.equals("Staff")) {
            //Toast.makeText(this, "Seleccion ${seleccion}", Toast.LENGTH_SHORT).show()

        }
    }

    private fun gameClicked(game: StudentHP) {
        Toast.makeText(this, "Clic en el elemento con títiulo ${game.actor}", Toast.LENGTH_SHORT)
            .show()

        val bundle = Bundle()

        bundle.putString("id", game.id)

        //val intent = Intent(this, Details::class.java)

        //intent.putExtras(bundle)

        //startActivity(intent)
    }
}