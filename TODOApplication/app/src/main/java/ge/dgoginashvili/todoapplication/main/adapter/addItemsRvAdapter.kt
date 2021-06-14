package ge.dgoginashvili.todoapplication.main.adapter


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.dgoginashvili.todoapplication.R
import ge.dgoginashvili.todoapplication.main.Utils.Utils
import ge.dgoginashvili.todoapplication.main.activity.AddPageActivity

class addItemRvAdapter(
    var todoitems: ArrayList<String>,
    var doneItems: ArrayList<String>,
    var addActivity: AddPageActivity
) :

    RecyclerView.Adapter<itemHolder>() {
    override fun getItemCount(): Int {
        return todoitems.size + doneItems.size
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        if (position <= todoitems.size - 1) {
            holder.checkBox.text = todoitems[position]
            holder.checkBox.isChecked = false
            holder.closeButton.visibility = View.VISIBLE

        } else {
            holder.checkBox.text = doneItems[position - todoitems.size]
            holder.checkBox.isChecked = true
            holder.closeButton.visibility = View.INVISIBLE
        }
        holder.closeButton.setOnClickListener {
            if (position <= todoitems.size - 1) {
                todoitems.removeAt(position)
            } else {
                doneItems.removeAt(position - todoitems.size)
            }
            notifyDataSetChanged()
        }
        holder.checkBox.setOnClickListener {
            if (position <= todoitems.size - 1) {
                doneItems.add(todoitems[position])
                todoitems.removeAt(position)
            } else {
//                Log.d("Position", position.toString())
//                Log.d("todoList", todoitems.toString())
//                Log.d("doneList", doneItems.toString())
//                Log.d("index", (position - todoitems.size).toString())
                if (position == 0) {
                    todoitems.add(doneItems[position])
                    doneItems.removeAt(position)
                } else {
                    var s = doneItems[position - todoitems.size]
                    doneItems.removeAt(position - todoitems.size)
                    todoitems.add(s)
                }
            }
            notifyDataSetChanged()
            addActivity.notifyChangedLists(todoitems, doneItems)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemmodel, parent, false)
        return itemHolder(view)
    }
}

class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
    var closeButton = itemView.findViewById<Button>(R.id.delButton)
}