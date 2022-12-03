import kotlin.system.measureTimeMillis

fun main() {
    println("Advent of code: Day ....")
    val timeInMillis = measureTimeMillis {
        val day = 3
        val data = DataSource.getDataAsList(day)
        val result = Day3.puzzleB(data)

        println("Puzzle day $day = $result")
    }
    println("(The execution took $timeInMillis ms)")
}
