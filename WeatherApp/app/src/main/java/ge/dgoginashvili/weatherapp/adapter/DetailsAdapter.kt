
package ge.dgoginashvili.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.dgoginashvili.weatherapp.R

class DetailsAdapter(var list: ArrayList<Pair<String,String>>) : RecyclerView.Adapter<DitItemViewHolder>() {
    override fun getItemCount(): Int {
        Log.d("SIZeEE",list.size.toString())
        return list.size
    }
    override fun onBindViewHolder(holder: DitItemViewHolder, position: Int) {
        val item = list[position]
        holder.detailText.text = item.first
        holder.detailValue.text = item.second
        Log.d("NAMEES",item.first + " " + item.second)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DitItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.details_item, parent, false)
        return DitItemViewHolder(view)
    }
}

class DitItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var detailText = itemView.findViewById<TextView>(R.id.txt_dit_text)
    var detailValue = itemView.findViewById<TextView>(R.id.txt_dit_value)
}
