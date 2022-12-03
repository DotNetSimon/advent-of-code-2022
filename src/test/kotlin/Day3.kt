import org.junit.Test
class Day3Test {
    val data = """
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
""".trimIndent().split("\n")

    @Test
    fun ifBasicDataThenCorrectValues() {
        println(Day3.puzzleA(data))
    }

    @Test
    fun ifBasicDataPuzzleBThenCorrectValues() {
        println(Day3.puzzleB(data))
    }
}