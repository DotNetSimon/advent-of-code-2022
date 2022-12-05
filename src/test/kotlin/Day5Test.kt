import org.junit.Test

class Day5Test {
    val data = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
    """.trimIndent().split("\n").filterNot{it == ""}

    @Test
    fun puzzleACorrect() {
        val actual = Day5.puzzleA(data)
        println(actual)
        assert("CMZ" == actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = Day5.puzzleB(data)
        println(actual)
        assert("MCD" == actual)
    }

}