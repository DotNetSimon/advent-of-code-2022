import org.junit.Test

class Day8Test {
    val data = """30373
25512
65332
33549
35390""".trimIndent().split("\n").filterNot{it == ""}

    @Test
    fun puzzleACorrect() {
        val actual = Day8.puzzleA(data)
        println(actual)
        assert(21 == actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = Day8.puzzleB(data)
        println(actual)
        assert(8 == actual)
    }

}