import kotlin.collections.ArrayDeque
import kotlin.math.abs

class Day12: Day {
    data class Point(val x: Int, val y: Int) {
        fun left() = Point(x-1, y)
        fun left(distance: Int) = Point(x-distance, y)
        fun right() = Point(x+1,y)
        fun right(distance: Int) = Point(x+distance, y)
        fun up() = Point(x, y-1)
        fun down() = Point(x, y+1)
        fun downLeft() = Point(x-1,y+1)
        fun downRight() = Point(x+1,y+1)
    }
    data class Node(val point: Point, val height: Int, val steps: Int, val parent: Node?) {
        fun getPath(): MutableList<Node> {
            if (parent == null) { return mutableListOf(this) }
            val result = parent.getPath()
            result.add(this)
            return result
        }

        companion object {
            fun diff(current: Node, height: Int) = height - current.height
        }
    }

    data class HeightMap(val map: MutableList<List<Char>>)
    {
        fun find(char: Char): Point {
            for (y in 0 until map.size) {
                for (x in 0 until map[y].size) {
                    if (map[y][x] == char) {
                        return Point(x,y)
                    }
                }
            }
            throw Exception("Couldn't find point $char")
        }

        fun charAt(point: Point) = map[point.y][point.x]

        fun heightAt(point: Point): Int {
            val ch = map[point.y][point.x]
            if (ch == 'S') return 'a'.code
            if (ch == 'E') return 'z'.code
            return ch.code
        }

        fun neighbors(current: Node): List<Node> {
            val point = current.point
            val options = mutableListOf<Node>()
            if (point.x > 0) {
                options.add(Node(point.left(), heightAt(point.left()), current.steps + 1, current))
            }
            if (point.x < map[0].size-1) {
                options.add(Node(point.right(), heightAt(point.right()), current.steps + 1, current))
            }
            if (point.y > 0) {
                options.add(Node(point.up(), heightAt(point.up()), current.steps + 1, current))
            }
            if (point.y < map.size-1) {
                options.add(Node(point.down(), heightAt(point.down()), current.steps + 1, current))
            }
            return options.filterNot { it.point == current.parent?.point }
        }
        fun options(current: Node): List<Node> {
            val point = current.point
            val options = mutableListOf<Node>()
            if (point.x > 0) {
                val p = point.left()
                addOption(current, p, options)
            }
            if (point.x < map[0].size-1) {
                val p = point.right()
                addOption(current, p, options)
            }
            if (point.y > 0) {
                val p = point.up()
                addOption(current, p, options)
            }
            if (point.y < map.size-1) {
                val p = point.down()
                addOption(current, p, options)
            }
            return options.filterNot { it.point == current.parent?.point }
        }

        private fun addOption(current: Node, p: Point, options: MutableList<Node>) {
            if (Node.diff(current, heightAt(p)) <= 1) {
                options.add(Node(p, heightAt(p), current.steps + 1, current))
            }
        }
    }
    override fun puzzleA(data: List<String>): String {
        val heightMap = HeightMap(mutableListOf<List<Char>>())
        for (line in data) {
            heightMap.map.add(line.toList())
        }
        val start = heightMap.find('S')
        val end = heightMap.find('E')
        val route = findRoute(heightMap, start, 'E')

        return (route.size -1 ).toString()
    }

    private fun findRoute(heightMap: HeightMap, start: Point, end: Char): List<Node> {
        val openQueue = ArrayDeque<Node>()
        val closed = mutableListOf<Node>()
        openQueue.add(Node(start, 'a'.code, 0, null))

        while (openQueue.size > 0) {
            val current = openQueue.removeFirst()
            if (heightMap.charAt(current.point) == end) {
                return current.getPath()
            }
            val options = heightMap.options(current)
            openQueue.addAll(
                options
                    .filterNot { option -> closed.any{ it.point == option.point } }
                    .filterNot { option -> openQueue.any { it.point == option.point} })
            closed.add(current)
        }
        val highest = closed.maxBy{ it.height }
        println("Didn't find route. Highest point: ${highest.height.toChar()}, node: $highest")
        throw Exception("No Route Found")
    }

    private fun findRoute2(heightMap: HeightMap, start: Point, end: Char): List<Node> {
        val openQueue = ArrayDeque<Node>()
        val closed = mutableListOf<Node>()
        openQueue.add(Node(start, heightMap.heightAt(start), 0, null))

        while (openQueue.size > 0) {
            val current = openQueue.removeFirst()
            if (heightMap.charAt(current.point) == end) {
                return current.getPath()
            }
            val options = heightMap.neighbors(current).filter { current.height - it.height <= 1 }
            openQueue.addAll(
                options
                    .filterNot { option -> closed.any{ it.point == option.point } }
                    .filterNot { option -> openQueue.any { it.point == option.point} })
            closed.add(current)
        }
        val highest = closed.minBy{ it.height }
        println("Didn't find route. lowest point: ${highest.height.toChar()}, node: $highest")
        throw Exception("No Route Found")
    }
    override fun puzzleB(data: List<String>): String {
        val heightMap = HeightMap(mutableListOf())
        for (line in data) {
            heightMap.map.add(line.toList())
        }
        val start = heightMap.find('E')
        val route = findRoute2(heightMap, start, 'a')

        return (route.size -1 ).toString()
    }
}