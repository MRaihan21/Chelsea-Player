package com.chelseaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListPlayerAdapter(private val listPlayer: ArrayList<Player>): RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_player, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlayer.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val player = listPlayer[position]

        Glide.with(holder.itemView.context)
            .load(player.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = player.name
        holder.tvDescription.text = player.description
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih " + listPlayer[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }

        val (name, detail, photo) = listPlayer[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = detail
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPlayer[holder.adapterPosition])
        }

//        val (name, description, photo) = listPlayer[position]
//        holder.tvName.text = name
//        holder.tvDescription.text = description
//        holder.imgPhoto.setImageResource(photo)
//
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listPlayer[holder.adapterPosition])
//        }
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Player)
    }
}
