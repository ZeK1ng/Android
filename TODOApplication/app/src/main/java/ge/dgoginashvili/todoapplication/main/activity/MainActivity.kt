package ge.dgoginashvili.todoapplication.main.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import ge.dgoginashvili.todoapplication.R
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import ge.dgoginashvili.todoapplication.main.Interfaces.MainViewInterface
import ge.dgoginashvili.todoapplication.main.Presenter
import ge.dgoginashvili.todoapplication.main.Utils.Utils
import ge.dgoginashvili.todoapplication.main.adapter.mainRecvAdapter

class MainActivity : AppCompatActivity(),
    MainViewInterface {
    private lateinit var presenter: Presenter
    lateinit var data: List<todoEntity>
    lateinit var pinnedText: TextView
    lateinit var otherText: TextView
    lateinit var pinnedRecView: RecyclerView
    lateinit var otherRecView: RecyclerView
    lateinit var mnOtherAdapter: mainRecvAdapter
    lateinit var pinnedAdapter: mainRecvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        pinnedText = findViewById(R.id.pinnedText)
        otherText = findViewById(R.id.otherText)
        pinnedRecView = findViewById(R.id.pinnedRecView)
        otherRecView = findViewById(R.id.otherRecView)
        pinnedText.visibility = View.INVISIBLE
        otherText.visibility = View.INVISIBLE
        presenter = Presenter(this, null)
//        presenter.purgeDB()
        presenter.getDBData()
        findViewById<FloatingActionButton>(R.id.imageButton2).scaleType = ImageView.ScaleType.CENTER
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }


    override fun showData(tde: List<todoEntity>) {
        Log.d("WAZAA","WAAAAAAAAAAAA")
        val sorted = tde.sortedWith(Comparator{ent1,ent2 -> Utils.compareByDate(ent1,ent2)})
        mnOtherAdapter = mainRecvAdapter(sorted,this)
        val pinnedList : List<todoEntity>
        val nonPinnedList: List<todoEntity>
        if(presenter.hasPinned(sorted)){
            pinnedText.visibility = View.VISIBLE
            pinnedList= presenter.getPinnedItems(sorted)!!
            nonPinnedList = presenter.getNonPinned(sorted)!!
            if(nonPinnedList.isNotEmpty()){
                otherText.visibility = View.VISIBLE
            }
        }else{
            otherRecView.adapter = mnOtherAdapter
            otherRecView.layoutManager = GridLayoutManager(this, 2)
            mnOtherAdapter.notifyDataSetChanged()
        }
//        Log.d("DB", tde.toString())
    }
    fun itemClicked(td:todoEntity){
        val intent = Intent(this, AddPageActivity::class.java)
        val ls = Gson().toJson(td)
        intent.putExtra(Utils.getEntityExtraName(),ls)
        startActivity(intent)
        finish()
    }
    fun addButtonClicked(view: View) {
        Log.d("Going To new Activity", "Activity go BRRRRRR")
        Log.d("Time", Utils.getTime())
        val intent = Intent(this, AddPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    //TODO
    /****
     * 1) add new activity to add tasks
     * 2) handle task creation
     * 3) print new taks on firt page
     * 4) add graphics for first page
     * 5)handle pinned stuff
     * 6) handle search
     * ----------------
     * main viewdan ro recycel adapters sheedzlos intentis shecvla , mainview gadavce adapters da iqidan gamovidzaxo funqcia inteteis shecvlis romelsac gavatan chems todoEntitys.
     *
     */

}