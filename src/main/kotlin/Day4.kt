class Day4 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            var score = 0

            for (line in data) {
                val pairs = line.split(",")
                val first = pairs[0].split("-").map { it.toInt() }
                val second = pairs[1].split("-").map { it.toInt() }

                if ((first[0] >= second[0] && first[1] <= second[1]) || (second[0] >= first[0] && second[1] <= first[1])) {
                    // this pair has one of their sections entirely overlapped by the other
                    score++
                }

            }
            return score
        }

        fun puzzleB(data: List<String>): Int {
            var score = 0
            for (line in data) {
                val pairs = line.split(",")
                val first = pairs[0].split("-").map { it.toInt() }
                val second = pairs[1].split("-").map { it.toInt() }

                if ((first[0] >= second[0] && first[0] <= second[1]) ||
                    (first[1] >= second[0] && first[1] <= second[1]) ||
                    (second[0] >= first[0] && second[0] <= first[1]) ||
                    (second[1] >= first[0] && second[1] <= first[1])
                    ) {
                    // this pair has one of their sections entirely overlapped by the other
                    score++
                }
            }
            return score
        }
    }
}
