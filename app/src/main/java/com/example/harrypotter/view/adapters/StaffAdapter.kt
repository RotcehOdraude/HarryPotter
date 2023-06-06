package com.example.harrypotter.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter.databinding.GameElementBinding
import com.example.harrypotter.model.StaffHP
import com.example.harrypotter.model.StudentHP

class StaffAdapter(
    private var context: Context,
    private var students: ArrayList<StaffHP>,
    private val clickListener: (StaffHP) -> Unit
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(view: GameElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStudent = view.ivThumbnail
        val tvName = view.tvTitle
        val tvDeveloper = view.tvDeveloper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = students[position].name

        Glide.with(context)
            .load(students[position].image)
            .into(holder.ivStudent)

        //holder.tvDeveloper.text = "EA Sports"

        holder.itemView.setOnClickListener {
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(students[position])
        }
    }
}