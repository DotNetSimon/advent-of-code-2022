import org.junit.Test

class Day9Test {
    val data = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2""".trimIndent().split("\n").filterNot{it == ""}

    val data2 = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20""".trimIndent().split("\n").filterNot{it == ""}

    @Test
    fun puzzleACorrect() {
        val actual = Day9.puzzleA(data)
        println(actual)
        assert(13 == actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = Day9.puzzleB(data2)
        println(actual)
        assert(36 == actual)
    }

}