package ge.dgoginashvili.todoapplication.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import ge.dgoginashvili.todoapplication.R


class itemContRecvAdapter(
    var todoitems: ArrayList<String>,
    var doneItems: ArrayList<String>
) :

    RecyclerView.Adapter<itemContHolder>() {

    override fun getItemCount(): Int {
        return todoitems.size + doneItems.size
    }

    override fun onBindViewHolder(holder: itemContHolder, position: Int) {
        holder.closeButton.visibility=View.INVISIBLE
        if (position <= todoitems.size - 1) {
            holder.checkBox.text = todoitems[position]
            holder.checkBox.isChecked = false
        } else {
            holder.checkBox.text = doneItems[position - todoitems.size]
            holder.checkBox.isChecked = true
        }
        holder.checkBox.isClickable=false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemContHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemmodel, parent, false)
        return itemContHolder(view)
    }
}


class itemContHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
    var closeButton = itemView.findViewById<Button>(R.id.delButton)
}