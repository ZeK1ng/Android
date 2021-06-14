package ge.dgoginashvili.todoapplication.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.dgoginashvili.todoapplication.R
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import ge.dgoginashvili.todoapplication.main.Interfaces.AddViewInterface
import ge.dgoginashvili.todoapplication.main.Presenter
import ge.dgoginashvili.todoapplication.main.Utils.Utils
import ge.dgoginashvili.todoapplication.main.adapter.addItemRvAdapter


class AddPageActivity : AppCompatActivity(),AddViewInterface {
    private var isPinActive: Boolean = false
    private var todoItems = arrayListOf<String>()
    private var doneItems = arrayListOf<String>()
    lateinit var titleView: TextView
    lateinit var itemView: TextView
    lateinit var recview: RecyclerView
    lateinit var adapter: addItemRvAdapter
    lateinit var tde: todoEntity
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)
        titleView = findViewById(R.id.titleText)
        itemView = findViewById(R.id.itemText)
        recview = findViewById(R.id.rv_items)
        adapter = addItemRvAdapter(todoItems, doneItems, this)
        presenter = Presenter(null,this)

        recview.adapter = adapter
        recview.hasFixedSize()
        recview.layoutManager = LinearLayoutManager(this)
        findViewById<ImageButton>(R.id.pinButton).setOnClickListener {
            pinItem()
        }
        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            goBackToMainActivity()
        }
        findViewById<Button>(R.id.addItemBUtton).setOnClickListener {
            addItemToList()
        }
    }

    fun notifyChangedLists(td: ArrayList<String>, dn: ArrayList<String>) {
        todoItems = td
        doneItems = dn
        Log.d("todoList", todoItems.toString())
        Log.d("doneList", doneItems.toString())
    }


    private fun addItemToList() {
        if (itemView.text.toString() != "") {
            todoItems.add(itemView.text.toString())
            adapter.notifyDataSetChanged()
        }
    }
    private fun saveEntity(){
        tde = todoEntity(titleView.text.toString(),todoItems,doneItems,isPinActive, Utils.getTime())
        presenter.saveEntity(tde)
    }
    private fun goBackToMainActivity() {
        saveEntity()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goBackToMainActivity()
    }

    private fun pinItem() {
        if (isPinActive) {
            isPinActive = false
            findViewById<ImageButton>(R.id.pinButton).setImageResource(R.drawable.ic_pin_small)
        } else {
            isPinActive = true
            findViewById<ImageButton>(R.id.pinButton).setImageResource(R.drawable.ic_pinned_small)
        }
    }

    override fun racxa(text: String) {
        Log.d("racxa","rucxa")
    }
}