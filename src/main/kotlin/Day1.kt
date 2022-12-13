class Day1{

    companion object {
        private val day = 1
        val data = DataSource.getDataAsList(day)

        // fairly straightforward summing of all values to see which one is hte highest.
        fun puzzleA() {
            var max = 0
            var current = 0
            for (line in data) {
                if (line == "") {
                    if (current > max) {
                        max = current
                    }
                    current = 0
                } else {
                    current += line.toInt()
                }
            }

            println(max)
        }

        // straightforward summing and storing each elf's calories and then sorting the list to find the top 3.
        fun puzzleB() {
            val totals = mutableListOf<Int>()
            var current = 0
            for (line in data) {
                if (line == "") {
                    totals.add(current)
                    current = 0
                } else {
                    current += line.toInt()
                }
            }
            totals.sortDescending()
            println(totals.take(3).sum())
        }
    }
}
