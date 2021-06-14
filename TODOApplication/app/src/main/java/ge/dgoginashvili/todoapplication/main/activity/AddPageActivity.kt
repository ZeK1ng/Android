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
import com.google.gson.Gson
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
    private var haveTde:Boolean = false
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)
        init()
        getIntentData()
        setupButtons()
    }
    private fun getIntentData(){
        val intent = intent
        val tdStr = intent.getStringExtra(Utils.getEntityExtraName())
        if(tdStr != null){
            haveTde = true
            tde = Gson().fromJson(tdStr,todoEntity::class.java)
            if(tde.title != ""){
                titleView.text = tde.title
            }
            if(tde.pinned){
                isPinActive = true
                findViewById<ImageButton>(R.id.pinButton).setImageResource(R.drawable.ic_pinned_small)
            }
            if (tde.tasks.size !=0 || tde.done.size != 0){
                notifyChangedLists(tde.tasks,tde.done)
            }
        }

    }
    private fun init(){
        titleView = findViewById(R.id.titleText)
        itemView = findViewById(R.id.itemText)
        recview = findViewById(R.id.rv_items)
        adapter = addItemRvAdapter(todoItems, doneItems, this)
        presenter = Presenter(null,this)
        recview.adapter = adapter
        recview.hasFixedSize()
        recview.layoutManager = LinearLayoutManager(this)
    }
    private fun setupButtons(){
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
        for (s in td) {
            todoItems.add(s)
        }
        for( s in dn){
            doneItems.add(s)
        }
        Log.d("todoList", todoItems.toString())
        Log.d("doneList", doneItems.toString())
        adapter.notifyDataSetChanged()
    }


    private fun addItemToList() {
        if (itemView.text.toString() != "") {
            todoItems.add(itemView.text.toString())
            adapter.notifyDataSetChanged()
        }
    }
    private fun saveEntity(){
        if(todoItems.size !=0 || doneItems.size != 0){
            if(haveTde){
                tde.done = doneItems
                tde.tasks = todoItems
                tde.date = Utils.getTime()
                tde.title = titleView.text.toString()
                tde.pinned = isPinActive
                presenter.updateEntity(tde)
            }else{
                tde = todoEntity(titleView.text.toString(),todoItems,doneItems,isPinActive, Utils.getTime())
                presenter.saveEntity(tde)
            }
        }
        if(haveTde && todoItems.size == 0 && doneItems.size == 0){
            presenter.deleteEntity(tde)
        }

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