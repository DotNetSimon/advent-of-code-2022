import java.util.*

class Day6 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            val line = data[0]
            var index = 0
            line.windowed(4).forEach{ if (it.all(hashSetOf<Char>()::add)) { println(it); return index+4 } else {index++ } }
            return index
        }
        fun puzzleB(data: List<String>): Int {
            val searchWidth = 14
            val line = data[0]
            var index = 0
            line.windowed(searchWidth).forEach{ if (it.all(hashSetOf<Char>()::add)) { println(it); return index+searchWidth } else {index++ } }
            return index
        }
    }
}
