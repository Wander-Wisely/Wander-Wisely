package id.com.wanderwisely.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity
import id.com.wanderwisely.databinding.ItemFavoriteBinding


class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val wisata = ArrayList<FavoriteEntity>()

    fun setList(wisatas : ArrayList<FavoriteEntity>){
        wisata.clear()
        wisata.addAll(wisatas)
        notifyDataSetChanged()
    }
    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int = wisata.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.binding.itemTouristName.text = wisata[position].name
        holder.binding.tvLocation.text = wisata[position].city
        val fromPrice = wisata[position].costFrom
        val costTo = wisata[position].costTo
        val totalPrice = fromPrice + costTo
        val priceText = if (totalPrice == 0) {
            "Free"
        } else {
            "Rp. ${(totalPrice / 2)}"
        }
        holder.binding.tvPrice.text = priceText
        val imageUrl = wisata[position].path
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.photo)

    }
}