package com.reev.gotrav

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reev.gotrav.databinding.ItemRowWisataBinding

class ListWisataAdapter(private val listWisata: ArrayList<Wisata>) : RecyclerView.Adapter<ListWisataAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowWisataBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listWisata[position]
        //buat nampilin data
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.titleTextView.text = name
        holder.binding.descriptionTextView.text = description

        holder.itemView.setOnClickListener {
            //intent ke DetailActivity
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_wisata", listWisata[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)

            onItemClickCallback.onItemClicked(listWisata[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listWisata.size

    class ListViewHolder(var binding: ItemRowWisataBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Wisata)
    }
}