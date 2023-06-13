package id.com.wanderwisely.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.databinding.ItemRecommendationBinding
import id.com.wanderwisely.ui.detail.DetailActivity

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {
    private val dataItems: MutableList<DataItem> = mutableListOf()

    class RecommendViewHolder(private val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata: DataItem) {
            binding.touristName.text = wisata.name
            binding.tvLocationRecommendation.text = wisata.city
            binding.ratingRecommend.text = wisata.rating
            val imagePath = wisata.path?.firstOrNull()
            Glide.with(itemView.context)
                .load(imagePath)
                .into(binding.photoRecommendation)

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                intentDetail.putExtra("id", wisata.id)
                intentDetail.putExtra("lat", wisata.latitude)
                intentDetail.putExtra("lon", wisata.longitude)
                intentDetail.putExtra("costto", wisata.costTo)
                intentDetail.putExtra("costfrom", wisata.costFrom)
                intentDetail.putExtra("tourismfile", wisata.path?.firstOrNull().toString())
                intentDetail.putExtra("name", wisata.name)
                itemView.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val wisata = dataItems[position]
        holder.bind(wisata)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<DataItem>) {
        dataItems.clear()
        dataItems.addAll(newData)
        notifyDataSetChanged()
    }
}