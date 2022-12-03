class Day3 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            var score = 0

            for (line in data) {
                if (line.length <= 2) {
                    println("odd line: \"$line\""); continue
                }
                val length = line.length / 2
                val set1 = line.slice(0 until length).toList()
                val set2 = line.slice(length until line.length).toList()
                val i = set1.intersect(set2)
                val code = i.first().code
                if (i.first() >= 'a') {
                    score += code - 96
                } else {
                    score += code - 38
                }
            }
            return score
        }

        fun puzzleB(data: List<String>): Int {
            var score = 0

            val group = mutableListOf<Set<Char>>()
            var count = 0
            for (line in data) {
                if (line.length <= 2) {
                    println("odd line: \"$line\""); continue
                }
                group.add(line.toSet())
                count++
                if (count == 3) {
                    val badge = group[0].intersect(group[1]).intersect(group[2])
                    val code = badge.first().code
                    if (badge.first() >= 'a') {
                        score += code - 96
                    } else {
                        score += code - 38
                    }
                    group.clear()
                    count = 0
                }
            }
            return score
        }
    }
}
