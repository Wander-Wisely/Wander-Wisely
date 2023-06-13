package id.com.wanderwisely.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.databinding.ItemPlanBinding
import id.com.wanderwisely.ui.detail.DetailActivity

class PlanAdapter :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    private val wisata = ArrayList<PlanEntity>()
    private var onFavoriteClickListener: ((PlanEntity) -> Unit)? = null
    fun setList(wisatas: ArrayList<PlanEntity>) {
        wisata.clear()
        wisata.addAll(wisatas)
        notifyDataSetChanged()
    }

    inner class PlanViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(favorite : PlanEntity){
            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                intentDetail.putExtra("id", favorite.id)
                intentDetail.putExtra("costto", favorite.costTo)
                intentDetail.putExtra("costfrom", favorite.costFrom)
                intentDetail.putExtra("tourismfile", favorite.path)
                intentDetail.putExtra("city", favorite.city)
                intentDetail.putExtra("name", favorite.name)
                itemView.context.startActivity(intentDetail)
            }
            binding.favorite.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = wisata[position]
                    onFavoriteClickListener?.invoke(item)
                }
            }
        }
        }
    fun setOnFavoriteClickListener(listener: (PlanEntity) -> Unit) {
        onFavoriteClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.PlanViewHolder {
        val binding =
            ItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        holder.bind(wisata[position])
        holder.binding.favorite.setOnClickListener {
            val item = wisata[position]
            onFavoriteClickListener?.invoke(item)
        }
    }}