package ge.dgoginashvili.weatherapp.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.dgoginashvili.weatherapp.R

class hourlyDetailsAdapter(var list: ArrayList<Map<String, String>>) :
    RecyclerView.Adapter<hourlyItemViewHolder>() {
    override fun getItemCount(): Int {
        Log.d("SIZeEE", list.size.toString())
        return list.size
    }

    override fun onBindViewHolder(holder: hourlyItemViewHolder, position: Int) {
        val itemMap = list[position]
        Log.d("MAAP",itemMap.toString())
        holder.time.text = itemMap["date"]
        holder.temp.text = itemMap["temp"]
        holder.description.text = itemMap["desc"]
        val iconUrl = itemMap["iconUrl"]
        Glide.with(holder.itemView.context)
            .load(iconUrl)
            .into(holder.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hourlyItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_item, parent, false)
        return hourlyItemViewHolder(view)
    }
}

class hourlyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var time = itemView.findViewById<TextView>(R.id.time_txt_hourly)
    var temp = itemView.findViewById<TextView>(R.id.temper_value_hourly)
    var description = itemView.findViewById<TextView>(R.id.descr_text_hourly)
    var icon = itemView.findViewById<ImageView>(R.id.iconImageHourly)

}