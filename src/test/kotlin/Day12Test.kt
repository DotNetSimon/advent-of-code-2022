import org.junit.Test

class Day12Test {
    val data = """Sabqponm
abcryxxl
accsEzxk
acctuvwj
abdefghi""".trimIndent().split("\n").filterNot{it == ""}

    val data2 = """Sabqponm
abcryxxl
dddszExk
caatuvwj
bcdefghi""".trimIndent().split("\n").filterNot{it == ""}


    val day = DayFactory.createDay(12)
    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(data)
        println(actual)
        assert("31" == actual)
    }

    @Test
    fun puzzleACorrectGoingDown() {
        val actual = day.puzzleA(data2)
        println(actual)
        assert("35" == actual)
    }
    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(data)
        println(actual)
        assert("28" == actual)
    }

}