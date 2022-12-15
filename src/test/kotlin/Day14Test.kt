import junit.framework.TestCase.assertEquals
import org.junit.Test

class Day14Test {
    val day = DayFactory.createDay(14)

    val data = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
""".trimIndent().split("\n").filterNot{it == ""}


    val expected = """0 ......+...
1 ..........
2 ......o...
3 .....ooo..
4 ....#ooo##
5 ...o#ooo#.
6 ..###ooo#.
7 ....oooo#.
8 .o.ooooo#.
9 #########.
"""

    val expected2 = """............o............
...........ooo...........
..........ooooo..........
.........ooooooo.........
........oo#ooo##o........
.......ooo#ooo#ooo.......
......oo###ooo#oooo......
.....oooo.oooo#ooooo.....
....oooooooooo#oooooo....
...ooo#########ooooooo...
..ooooo.......ooooooooo..
#########################"""
    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(data)
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected2, actual)

    }

}