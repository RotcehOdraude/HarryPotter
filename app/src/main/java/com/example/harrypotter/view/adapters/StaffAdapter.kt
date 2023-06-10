package com.example.harrypotter.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter.R
import com.example.harrypotter.databinding.CharacterElementBinding
import com.example.harrypotter.model.StaffHP
import com.example.harrypotter.model.StudentHP

class StaffAdapter(
    private var context: Context,
    private var students: ArrayList<StaffHP>,
    private val clickListener: (StaffHP) -> Unit
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(view: CharacterElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStaff = view.ivThumbnail
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

        if (!students[position].image?.isEmpty()!!) {
            Glide.with(context)
                .load(students[position].image)
                .into(holder.ivStaff)
        } else {
            holder.ivStaff.setImageResource(R.drawable.character)
        }

        holder.itemView.setOnClickListener {
            //Para programar los eventos click al elemento completo del ViewHolder
            clickListener(students[position])
        }
    }
}