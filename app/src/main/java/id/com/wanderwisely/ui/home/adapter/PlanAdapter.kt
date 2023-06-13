package id.com.wanderwisely.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.databinding.ItemAllTouristBinding

class PlanAdapter :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    private val wisata = ArrayList<PlanEntity>()

    fun setList(wisatas: ArrayList<PlanEntity>) {
        wisata.clear()
        wisata.addAll(wisatas)
        notifyDataSetChanged()
    }

    inner class PlanViewHolder(val binding: ItemAllTouristBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.PlanViewHolder {
        val binding =
            ItemAllTouristBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }

    override fun getItemCount(): Int = wisata.size

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
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

    }}