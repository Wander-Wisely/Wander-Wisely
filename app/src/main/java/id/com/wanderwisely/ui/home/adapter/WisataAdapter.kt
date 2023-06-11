package id.com.wanderwisely.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.ItemAllTouristBinding
import id.com.wanderwisely.ui.detail.DetailActivity

class WisataAdapter : PagingDataAdapter<WisataResponse, WisataAdapter.WisataViewHolder>(
    DIFF_CALLBACK
){
    class WisataViewHolder(private val binding :ItemAllTouristBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata : WisataResponse){
            binding.itemTouristName.text = wisata.name
            binding.tvLocation.text = wisata.city
            val fromPrice = wisata.costFrom
            val costTo = wisata.costTo
            val totalPrice = fromPrice + costTo
            val priceText = if (totalPrice == 0) {
                "Free"
            } else {
                "Rp. ${(totalPrice / 2)}"
            }
            binding.tvPrice.text = priceText
            val mediaItem = wisata.tourismFiles?.firstOrNull() // Assuming you want to load the first media item
            val imageUrl = mediaItem?.path
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(binding.photo)

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                intentDetail.putExtra("Id", wisata.id)
                itemView.context.startActivity(intentDetail)
            }
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
            override fun areItemsTheSame(oldItem: WisataResponse, newItem: WisataResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WisataResponse, newItem: WisataResponse): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}