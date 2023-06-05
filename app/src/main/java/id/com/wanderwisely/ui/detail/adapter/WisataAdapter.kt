package id.com.wanderwisely.ui.detail.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.ItemAllTouristBinding

class WisataAdapter : PagingDataAdapter<WisataResponse, WisataAdapter.WisataViewHolder>(DIFF_CALLBACK){
    class WisataViewHolder(val binding :ItemAllTouristBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata : WisataResponse){
            binding.itemTouristName.text = wisata.namaTempat
            binding.tvLocation.text = wisata.kota
            Glide.with(itemView.context)
                .load(wisata.media)
                .into(binding.photo)
        }

    }
    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val wisata = getItem(position)
        if(wisata != null){
            holder.bind(wisata)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding = ItemAllTouristBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WisataResponse>() {
            override fun areItemsTheSame(
                oldItem: WisataResponse,
                newItem: WisataResponse,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: WisataResponse,
                newItem: WisataResponse,
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}