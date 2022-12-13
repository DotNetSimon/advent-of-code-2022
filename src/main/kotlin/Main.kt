import kotlin.system.measureTimeMillis

fun main() {
    println("Advent of code: Day ....")
    val timeInMillis = measureTimeMillis {
        val day = 12
        val data = DataSource.getDataAsList(day)
        val dayClass = DayFactory.createDay(day)
        val resultA = dayClass.puzzleA(data)

        println("PuzzleA day $day = \n$resultA")


        val resultB = dayClass.puzzleB(data)
        println("PuzzleB day $day = \n$resultB")

    }
    println("(The execution took $timeInMillis ms)")
}
