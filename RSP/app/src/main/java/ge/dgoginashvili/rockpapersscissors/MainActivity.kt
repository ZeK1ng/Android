package ge.dgoginashvili.rockpapersscissors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var computerScore = 0
    var userScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playGame()
    }

    private fun playGame() {
        rockButtonListener()
        paperButtonListener()
        szrButtonListener()
    }

    private fun szrButtonListener() {
        findViewById<Button>(R.id.szrButton).setOnClickListener {
            findViewById<TextView>(R.id.computerScore).setTextColor(Color.BLACK)
            findViewById<TextView>(R.id.playerScore).setTextColor(Color.BLACK)
            Log.d("szrButton Pressed", "szrButton")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            findViewById<ImageView>(R.id.userImage).setImageResource(R.drawable.scissors_image)
            findViewById<ImageView>(R.id.userImage).visibility = View.VISIBLE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)

            if (computerTurn == "Rock") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = computerScore.toString()
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.rock_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Paper") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.paper_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Scissor") {
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.YELLOW)
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.YELLOW)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.scissors_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
        }
    }

    private fun paperButtonListener() {
        findViewById<Button>(R.id.paperButton).setOnClickListener {
            findViewById<TextView>(R.id.computerScore).setTextColor(Color.BLACK)
            findViewById<TextView>(R.id.playerScore).setTextColor(Color.BLACK)
            Log.d("paperButton Pressed", "paperButton")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            findViewById<ImageView>(R.id.userImage).setImageResource(R.drawable.paper_image)
            findViewById<ImageView>(R.id.userImage).visibility = View.VISIBLE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)

            if (computerTurn == "Rock") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.rock_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Paper") {
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.YELLOW)
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.YELLOW)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.paper_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Scissor") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = computerScore.toString()
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.scissors_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
        }
    }

    private fun rockButtonListener() {
        findViewById<Button>(R.id.rockButton).setOnClickListener {
            findViewById<TextView>(R.id.computerScore).setTextColor(Color.BLACK)
            findViewById<TextView>(R.id.playerScore).setTextColor(Color.BLACK)
            Log.d("Rock Pressed", "Rock")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)
            findViewById<ImageView>(R.id.userImage).setImageResource(R.drawable.rock_image)
            findViewById<ImageView>(R.id.userImage).visibility = View.VISIBLE
            if (computerTurn == "Rock") {
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.YELLOW)
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.YELLOW)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.rock_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Paper") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = computerScore.toString()
                findViewById<TextView>(R.id.computerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.paper_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
            if (computerTurn == "Scissor") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
                findViewById<TextView>(R.id.playerScore).setTextColor(Color.GREEN)
                findViewById<ImageView>(R.id.computerImage).setImageResource(R.drawable.scissors_image)
                findViewById<ImageView>(R.id.computerImage).visibility = View.VISIBLE
            }
        }
    }

    private fun getComputerTurn(): String {
        when (val randomInteger = (1..3).shuffled().first()) {
            1 -> return "Rock"
            2 -> return "Paper"
            3 -> return "Scissor"
            else -> {
                Log.e("RandomGenerator Failed", randomInteger.toString())
            }
        }
        return ""
    }
}