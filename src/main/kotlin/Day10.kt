import kotlin.math.sign

class Day10 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            var cycles = readCycles(data)
            var signalStrenght = 0
            for (i in 20 until 220+1 step 40) {
                val str = i * cycles[i-2]
                println("Strenght at $i is $str")
                signalStrenght += str
            }

            return signalStrenght
        }

        private fun readCycles(data: List<String>): MutableList<Int> {
            var cycles = mutableListOf<Int>()
            var x = 1
            for (line in data) {
                val commands = line.split(" ")
                when (commands[0]) {
                    "noop" -> cycles.add(x)
                    "addx" -> {
                        cycles.add(x)
                        x += commands[1].toInt()
                        cycles.add(x)
                    }
                }

            }
            return cycles
        }


        fun puzzleB(data: List<String>): String {
            var cycles = readCycles(data)
            var currentIndex = 0
            var result = mutableListOf<MutableList<String>>()

            var current = 1
            for (y in 0 until 6) {
                result.add(mutableListOf())
                for (x in 0 until 40) {
                    if (current >= x-1 && current <= x + 1) {
                        result[y].add("#")
                    } else {
                        result[y].add(".")
                    }
                    current = cycles[currentIndex++]
                }
            }
            return result.map { it.joinToString("")}.joinToString("\n")
        }
    }
}