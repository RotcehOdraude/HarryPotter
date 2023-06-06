package com.example.harrypotter.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter.databinding.CharacterElementBinding
import com.example.harrypotter.model.StudentHP

class StudentsAdapter(
    private var context: Context,
    private var students: ArrayList<StudentHP>,
    private val clickListener: (StudentHP) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {
    class ViewHolder(view: CharacterElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStudent = view.ivThumbnail
        val tvName = view.tvCharacterName
        val tvActor = view.tvActorName
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = students.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = students[position].name
        holder.tvActor.text = students[position].actor

        Glide.with(context)
            .load(students[position].image)
            .into(holder.ivStudent)

        holder.itemView.setOnClickListener {
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(students[position])
        }
    }
}