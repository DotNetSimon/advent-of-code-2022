import java.util.*

class Day5 {

    companion object {

        fun puzzleA(data: List<String>): String {
            val (stackMaxHeight, boxStacks) = readStacks(data)

            for (line in data.slice(stackMaxHeight + 1..data.size - 1)) {

                // read and handle instructions
                val instr = line.split(" ")
                val amount = instr[1].toInt()
                val from = instr[3].toInt()
                val to = instr[5].toInt()

                for (m in 0 until amount) {
                    boxStacks[to - 1].push(boxStacks[from - 1].pop())
                }
            }

            val result = boxStacks.map { it.pop() }
            return result.joinToString("")
        }

        // read the stack of boxes into a list of stacks, and return the max height along with it.
        private fun readStacks(data: List<String>): Pair<Int, List<Stack<Char>>> {
            var readingStack = true
            var stackLines = mutableListOf<String>()
            var nrOfStacks = 0

            var i = 0
            // find where instructions start and measure stack height and width
            while (data[i][1] != '1') {
                i++
                stackLines.add(data[i - 1]) // store all the lines since we're going through them anyway.
            }
            nrOfStacks = data[i].split(" ").filterNot { it == "" }.last().toInt()
            val stackMaxHeight = i
            var boxStacks = ReadStacks(nrOfStacks, stackLines)
            return Pair(stackMaxHeight, boxStacks)
        }

        // initialize stacks and read them all.
        private fun ReadStacks(
            nrOfStacks: Int,
            stackLines: MutableList<String>
        ): List<Stack<Char>> {
            var boxStacks = List<Stack<Char>>(nrOfStacks) { Stack() }

            for (line in stackLines.reversed()) {
                for (s in 0 until nrOfStacks) {
                    val letter = line[1 + (s * 4)]
                    if (letter != ' ') {
                        boxStacks[s].push(line[1 + (s * 4)])
                    }
                }
            }
            return boxStacks
        }

        fun puzzleB(data: List<String>): String {
            val pair = readStacks(data)
            val stackMaxHeight = pair.first
            var boxStacks = pair.second

            for (line in data.slice(stackMaxHeight + 1..data.size - 1)) {

                // read instructions
                val instr = line.split(" ")
                val amount = instr[1].toInt()
                val from = instr[3].toInt()
                val to = instr[5].toInt()

                val moveStack = mutableListOf<Char>()
                for (m in 0 until amount) {
                    moveStack.add(boxStacks[from - 1].pop())
                }
                for (box in moveStack.reversed()) {
                    boxStacks[to - 1].push(box)
                }
            }

            val result = boxStacks.map { it.pop() }
            return result.joinToString("")

        }
    }
}
