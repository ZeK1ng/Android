package ge.dgoginashvili.todoapplication.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import ge.dgoginashvili.todoapplication.R
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import ge.dgoginashvili.todoapplication.main.activity.MainActivity

class mainRecvAdapter(
    var data: List<todoEntity>,
    var mnActivity: MainActivity
) :

    RecyclerView.Adapter<TDitemHolder>() {
    private val viewPool = RecycledViewPool()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TDitemHolder, position: Int) {
        val currEntity = data[position]
        holder.title.text = currEntity.title
        val lmanager =
            LinearLayoutManager(holder.recView.context, LinearLayoutManager.VERTICAL, false)
        lmanager.initialPrefetchItemCount = currEntity.done.size + currEntity.tasks.size
        val subItemAdapter = itemContRecvAdapter(currEntity.tasks, currEntity.done)
        holder.recView.layoutManager = lmanager
        holder.recView.adapter = subItemAdapter
        holder.recView.setRecycledViewPool(viewPool)

        holder.container.setOnClickListener{
            mnActivity.itemClicked(currEntity)
        }
        holder.recView.setOnClickListener{
            mnActivity.itemClicked(currEntity)
        }
        if(currEntity.tasks.size>3){
            holder.dots.visibility = View.VISIBLE
            holder.dots.text="..."
        }else{
            holder.dots.visibility = View.INVISIBLE
        }
        if(currEntity.done.size>0){
            holder.doneItemsCount.visibility=View.VISIBLE
            val str = "+ " + currEntity.done.size.toString()+" checked items"
            holder.doneItemsCount.text = str
        }else{
            holder.doneItemsCount.visibility=View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TDitemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemparent, parent, false)
        return TDitemHolder(view)
    }
}


class TDitemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recView = itemView.findViewById<RecyclerView>(R.id.itemparentRecVie)
    var title = itemView.findViewById<TextView>(R.id.titlev)
    var dots = itemView.findViewById<TextView>(R.id.dots)
    var doneItemsCount = itemView.findViewById<TextView>(R.id.doneitemsNum)
    var container = itemView.findViewById<ConstraintLayout>(R.id.parentContainer)
}