import kotlin.system.measureTimeMillis

fun main() {
    println("Advent of code: Day ....")
    val timeInMillis = measureTimeMillis {
        val day = 7
        val data = DataSource.getDataAsList(day)
//        val result = Day7.puzzleA(data)
        val result = Day7.puzzleB(data)

        println("Puzzle day $day = $result")
    }
    println("(The execution took $timeInMillis ms)")
}
