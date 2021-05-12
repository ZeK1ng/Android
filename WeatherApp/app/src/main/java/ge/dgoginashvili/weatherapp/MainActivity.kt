package ge.dgoginashvili.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import ge.dgoginashvili.weatherapp.adapter.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var viewpager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()

    }

    private fun setupViewPager() {
        viewpager = findViewById(R.id.viewPager)
        viewpager.adapter = ViewPagerAdapter(this)
        val dailyButton = findViewById<ImageButton>(R.id.buttonDaily)
        val hourlyButton = findViewById<ImageButton>(R.id.buttonHourly)

        dailyButton.setOnClickListener {
            viewpager.setCurrentItem(viewpager.currentItem - 1)
        }
        hourlyButton.setOnClickListener {
            viewpager.setCurrentItem(viewpager.currentItem + 1)
        }

    }


}
