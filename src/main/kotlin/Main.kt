import kotlin.system.measureTimeMillis

fun main() {
    println("Advent of code: Day ....")
    val timeInMillis = measureTimeMillis {
        val day = 5
        val data = DataSource.getDataAsList(day)
//        val result = Day5.puzzleA(data)
        val result = Day5.puzzleB(data)

        println("Puzzle day $day = $result")
    }
    println("(The execution took $timeInMillis ms)")
}
