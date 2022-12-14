import org.junit.Test

class Day13Test {
    val day = DayFactory.createDay(13)

    val data = """[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]""".trimIndent().split("\n").filterNot{it == ""}


    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(data)
        println(actual)
        assert("13" == actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(data)
        println(actual)
        assert("140" == actual)
    }

}