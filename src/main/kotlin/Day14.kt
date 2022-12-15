import java.lang.Integer.max
import java.lang.Integer.min

class Day14 : Day {
    data class SandMap(val height: Int, val width: Int, val origin: Int, val map: MutableList<MutableList<Char>>, private var sand:Int =0)
    {

        /**
         * simulate a single point of sand falling down one 'step'.
         */
        fun sandFall(point: Point): Point {
            val options = listOf(point.down(), point.downLeft(), point.downRight())
            for (p in options) {
                if (p.x < 0 || p.y > height) { return p }
                if (map[p.y][p.x] == '.')
                    return p
            }
            return point
        }

        /**
         * 'spawn' a grain of sand and drop it until it stops
         */
        fun dropSand(): Int {
            var sandOrigin = Point(origin, 0)

            var stopFalling = false
            while (!stopFalling) {
                val newSand = sandFall(sandOrigin)
                if (newSand == sandOrigin) {
                    stopFalling = true
                }
                sandOrigin = newSand
                if (sandOrigin.x < 0 || sandOrigin.y > height || sandOrigin.y == 0) {
                    return sand // stop calling this. sand is falling off the 'map' or stuck in source.
                }
            }
            map[sandOrigin.y][sandOrigin.x] = 'o'
            return ++sand
        }

        companion object {

            /**
             * build 2d 'map' of the cave
             */
            fun fromData(data: List<String>, addFloor: Boolean): SandMap {
                val coordinatesOfLines = data
                    .map{it.split(" -> ")
                        .map {
                            val p = it.split(",")
                            Point(p[0].toInt(), p[1].toInt())
                        }
                    }

                var width = 0
                var maxHeight = 0
                var leftShift = 0
                var origin = 0

                // hack for 2nd part, could be generalized
                // normalizing the coordinates too so we dont end up with a ton of empty space on the left. easier for output
                if (addFloor) {
                    maxHeight = coordinatesOfLines.maxOf { it.maxBy { it.y }.y } + 3 // 2 extra for floor
                    val minWidth = 500-maxHeight
                    val maxWidth = 500+maxHeight
                    width = maxWidth - minWidth + 1
                    origin = 500 - minWidth
                    leftShift = 500 - origin
                } else {
                    val minWidth = coordinatesOfLines.minOf { it.minBy { it.x }.x }
                    val maxWidth = coordinatesOfLines.maxOf { it.maxBy { it.x }.x }
                    width = maxWidth - minWidth + 1
                    maxHeight = coordinatesOfLines.maxOf { it.maxBy { it.y }.y } + 1
                    origin = 500 - minWidth
                    leftShift = 500 - origin

                }

                val map = SandMap(maxHeight, width, origin, MutableList(maxHeight) { MutableList(width) { '.' } })
                if (addFloor) {
                    map.map[maxHeight-1] = MutableList(width) {'#'}
                }

                // draw 2d map
                for (input in coordinatesOfLines) {
                    val coords = input.map { it.left(leftShift) }

                    var current = coords[0]
                    for (point in coords) {
                        for (x in min(point.x, current.x) until (max(point.x, current.x))+1) {
                            map.map[current.y][x] = '#'
                        }
                        for (y in min(point.y, current.y) until (max(point.y, current.y))+1) {
                            map.map[y][current.x] = '#'
                        }
                        current = point
                    }
                }

                return map
            }
        }

        override fun toString(): String {
            var result = ""
            for (y in 0 until height) {
                result += "$y "
                for (x in 0 until width) {
                    if (y==0 && x == origin ) {
                        result += '+' // draw origin
                    } else {
                        result += map[y][x]
                    }
                }
                result += "\n"
            }

            return result
        }
    }
    override fun puzzleA(data: List<String>): String {
        val map = SandMap.fromData(data, false)

        return dropSandOnMap(map)

        return map.toString()
    }

    private fun dropSandOnMap(map: SandMap): String {
        var totalSand = 0
        while (true) {
            val newSand = map.dropSand()
            if (newSand == totalSand) {
                println(totalSand)
                return map.toString()
            }
            totalSand = newSand
        }
    }

    override fun puzzleB(data: List<String>): String {
        val map = SandMap.fromData(data, true)

        return dropSandOnMap(map)

        return map.toString()
    }


}