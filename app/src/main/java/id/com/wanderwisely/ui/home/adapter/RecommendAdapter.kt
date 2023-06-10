package id.com.wanderwisely.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.databinding.ItemRecommendationBinding
import id.com.wanderwisely.ui.detail.DetailActivity

class RecommendAdapter: PagingDataAdapter<DataItem, RecommendAdapter.RecommendViewHolder>(
    DIFF_CALLBACK
) {
    class RecommendViewHolder(private val binding : ItemRecommendationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata : DataItem){
            binding.touristName.text = wisata.name
            binding.tvLocationRecommendation.text = wisata.city
            binding.ratingRecommend.text = wisata.rating
//            val mediaItem = wisata.tourismFiles?.firstOrNull()
//            val imageUrl = mediaItem?.path
//            Glide.with(itemView.context)
//                .load(imageUrl)
//                .into(binding.photo)

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                intentDetail.putExtra("Id", wisata.id)
                itemView.context.startActivity(intentDetail)
            }
        }
    }
    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val wisata = getItem(position)
        if(wisata != null){
            holder.bind(wisata)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}