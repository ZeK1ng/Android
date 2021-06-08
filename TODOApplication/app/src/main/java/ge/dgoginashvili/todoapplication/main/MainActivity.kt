package ge.dgoginashvili.todoapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ge.dgoginashvili.todoapplication.R

class MainActivity : AppCompatActivity(),MainViewInterface {
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this)
        presenter.testDB()
    }
    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }


    override fun showText(text: String) {
        findViewById<TextView>(R.id.text).text = text
    }

}