package ge.dgoginashvili.rockpapersscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var computerScore = 0
    var userScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playGame()
    }

    fun playGame() {
        findViewById<Button>(R.id.rockButton).setOnClickListener {
            Log.d("Rock Pressed", "Rock")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)
            if (computerTurn == "Rock") {
                print("Show comp rock image")
            }
            if (computerTurn == "Paper") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = computerScore.toString()
            }
            if (computerTurn == "Scissor") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
            }
        }
        findViewById<Button>(R.id.paperButton).setOnClickListener {
            Log.d("paperButton Pressed", "paperButton")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)

            if (computerTurn == "Rock") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
            }
            if (computerTurn == "Paper") {

            }
            if (computerTurn == "Scissor") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = computerScore.toString()
            }
        }
        findViewById<Button>(R.id.szrButton).setOnClickListener {
            Log.d("szrButton Pressed", "szrButton")
            findViewById<TextView>(R.id.startText).visibility = View.GONE
            val computerTurn = getComputerTurn()
            Log.i("Computer Action=",computerTurn)

            if (computerTurn == "Rock") {
                computerScore++
                findViewById<TextView>(R.id.computerScore).text = userScore.toString()
            }
            if (computerTurn == "Paper") {
                userScore++
                findViewById<TextView>(R.id.playerScore).text = userScore.toString()
            }
            if (computerTurn == "Scissor") {

            }
        }

    }

    private fun getComputerTurn(): String {
        val randomInteger = (1..3).shuffled().first()
        when (randomInteger) {
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