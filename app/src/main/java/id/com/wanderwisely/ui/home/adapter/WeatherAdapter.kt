package id.com.wanderwisely.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.com.wanderwisely.R
import id.com.wanderwisely.data.model.response.ListItem
import id.com.wanderwisely.databinding.ItemWeatherBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val dataItems: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = dataItems[position]
        holder.bind(weather)
    }
    override fun getItemCount(): Int {
        return dataItems.size
    }

    class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cuaca: ListItem) {
            val decimalFormat = DecimalFormat("#.##")
            val dtTxt = cuaca.dtTxt
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            val date = dtTxt?.let { inputFormat.parse(it) }
            val formattedDate = date?.let { outputFormat.format(it) }
            val temperature = cuaca.main?.temp ?: 0.0
            val temperatureInCelsius = decimalFormat.format(temperature - 273.15)
            val feelsLike = cuaca.main?.feelsLike ?: 0.0
            val feelsLikeInCelsius = decimalFormat.format(feelsLike - 273.15)
            val weatherCondition = cuaca.weather?.getOrNull(0)?.main
            val drawableResId = when (weatherCondition) {
                "Thunderstorm" -> R.drawable.thunderstorm
                "Cloudy" -> R.drawable.cloudy
                "Clear" -> R.drawable.clearsky
                "Rain" -> {
                    val rainIntensity = cuaca.weather[0]?.description
                    when {
                        rainIntensity?.contains("light", ignoreCase = true) == true -> R.drawable.lightrain
                        rainIntensity?.contains("heavy", ignoreCase = true) == true -> R.drawable.heavyrain
                        else -> R.drawable.lightrain
                    }
                }
                else -> R.drawable.weather
            }
            binding.tvWeatherType.text = weatherCondition
            binding.tvLocation.text = formattedDate
            binding.tvSuhu.text = temperatureInCelsius
            binding.tvFeelsLike.text = binding.root.context.getString(R.string.feels_like, feelsLikeInCelsius)
            binding.icnWeather.setImageResource(drawableResId)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ListItem>) {
        dataItems.clear()
        dataItems.addAll(newData)
        notifyDataSetChanged()
    }
}