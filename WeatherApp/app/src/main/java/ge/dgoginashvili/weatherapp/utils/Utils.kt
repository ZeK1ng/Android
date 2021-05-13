package ge.dgoginashvili.weatherapp.utils

import java.math.RoundingMode
import java.text.DecimalFormat

class Utils {
    companion object {
        fun roundAndFormat(i: String): String {

            val index = i.indexOf('.')
            val ch = i[index + 1]
            val k = Integer.parseInt(ch.toString())
            val df = DecimalFormat("#")
            if (k >= 5) {
                df.roundingMode = RoundingMode.CEILING
            } else {
                df.roundingMode = RoundingMode.FLOOR
            }
            return df.format(i.toDouble()).toInt().toString()
        }
        fun getMonth(monthIndex: Int): String {
            var month = ""
            when (monthIndex) {
                1 -> month = "Jan"
                2 -> month = "Feb"
                3 -> month = "Mar"
                4 -> month = "Apr"
                5 -> month = "May"
                6 -> month = "Jun"
                7 -> month = "Jul"
                8 -> month = "Aug"
                9 -> month = "Sep"
                10 -> month = "Oct"
                11 -> month = "Nov"
                12 -> month = "Dec"
                else -> { // Note the block
                    month = "none"
                }
            }
            return month
        }
    }
}