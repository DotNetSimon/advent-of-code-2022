class DayFactory {
    companion object {
        fun createDay(day: Int): Day {
            return when(day) {
                12 -> Day12()
                13 -> Day13()
                else -> throw IllegalArgumentException("Day doesn't exist")
            }
        }
    }
}

interface Day {
    abstract fun puzzleA(data: List<String>): String
    abstract fun puzzleB(data: List<String>): String
}