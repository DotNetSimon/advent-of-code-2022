import org.junit.Test

class Day7Test {
    val data = """${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
    """.trimIndent().split("\n").filterNot{it == ""}

    @Test
    fun puzzleACorrect() {
        val actual = Day7.puzzleA(data)
        println(actual)
        assert(95437 == actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = Day7.puzzleB(data)
        println(actual)
        assert(24933642 == actual)
    }

}