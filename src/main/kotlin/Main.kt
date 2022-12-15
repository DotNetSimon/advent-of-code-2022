import kotlin.system.measureTimeMillis

fun main() {
    println("Advent of code: Day ....")
    val day = 15
    val data = DataSource.getDataAsList(day)
    val dayClass = DayFactory.createDay(day)

    val timeInMillis = measureTimeMillis {

        val resultA = dayClass.puzzleA(data)
        println("PuzzleA day $day = \n$resultA")

    }

    println("(The execution took $timeInMillis ms)")

    val timeInMillis2 = measureTimeMillis {

        val resultB = dayClass.puzzleB(data)
        println("PuzzleB day $day = \n$resultB")

    }
    println("(The execution took $timeInMillis2 ms)")

}
