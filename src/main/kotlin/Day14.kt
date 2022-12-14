
class Day14 : Day {
    data class SandMap(val height: Int, val width: Int, val map: MutableList<MutableList<Char>>)
    {
        companion object {
            fun fromData(data: List<String>): SandMap {
                for (input in data) {
                    val points = input.split(" -> ").map{
                        val p = it.split(",")
                        Day12.Point(p[0].toInt(),p[1].toInt())
                    }

                    var current = points[0]
                    for (point in points) {

                    }

                }
            }
        }
    }
    override fun puzzleA(data: List<String>): String {
        val map = Map(150, 510, mutableListOf())

    }

    override fun puzzleB(data: List<String>): String {
        TODO("Not yet implemented")
    }


}