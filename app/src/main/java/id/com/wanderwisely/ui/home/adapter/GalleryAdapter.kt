package id.com.wanderwisely.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.model.response.TourismFilesItem
import id.com.wanderwisely.databinding.ItemImageBinding

class GalleryAdapter(private val image : List<TourismFilesItem?>?) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    inner class GalleryViewHolder(val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int = image!!.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(image!![position]?.path)
            .into(holder.binding.galerryImage)
    }
}