import org.junit.Test

class Day4Test {
    val data = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8""".trimIndent().split("\n")

    val data2 = """
        1-1,1-1
        2-2,1-3
        1-3,2-2
        1-3,2-4
        2-4,1-3
        """.trimIndent().split("\n")
    @Test
    fun puzzleACorrect() {
        val actual = Day4.puzzleA(data2)
        println(actual)
        assert(3 == actual)
    }

}