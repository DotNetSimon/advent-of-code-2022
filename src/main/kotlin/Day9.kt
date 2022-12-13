class Day9 {

    data class Point(var x: Int, var y: Int) {
        fun plus(pointB: Point): Point {
            return Point(x + pointB.x, y + pointB.y)
        }

        fun follow(pointB: Point): Point {
            var newX = x
            var newY = y
            var isDiagonal = if (Math.abs(x - pointB.x) + Math.abs(y - pointB.y) > 2) 0 else 1
            if (pointB.x > x + isDiagonal) {
                newX++
            }
            if (pointB.x < x - isDiagonal) {
                newX--
            }
            if (pointB.y < y - isDiagonal) {
                newY--
            }
            if (pointB.y > y + isDiagonal) {
                newY++
            }

            return Point(newX, newY)
        }

    }

    companion object {

        val directions = mapOf(
            "R" to Point(1, 0),
            "L" to Point(-1, 0),
            "U" to Point(0, -1),
            "D" to Point(0, 1)
        )

        fun puzzleA(data: List<String>): Int {
            var head = Point(0, 0)
            var tail = Point(0, 0)

            var visitedLocations = mutableSetOf<Point>()
            for (line in data) {

                val instruction = line.split(" ")
                for (i in 0 until instruction[1].toInt()) {
                    visitedLocations.add(tail)
                    println("Head: $head, Tail: $tail")
                    head = head.plus(directions[instruction[0]]!!)
                    tail = tail.follow(head)
                }
            }

            return visitedLocations.size
        }


        fun puzzleB(data: List<String>): Int {
            var knots = MutableList(10) { Point(0, 0) }

            var visitedLocations = mutableSetOf<Point>()
            visitedLocations.add(knots.last())

            for (line in data) {

                val instruction = line.split(" ")
                for (i in 0 until instruction[1].toInt()) {
                    println("Head: ${knots.first()}, Tail: ${knots.last()}")
                    knots[0] = knots.first().plus(directions[instruction[0]]!!)
                    knots.slice(1..knots.size - 1)
                        .forEachIndexed { index, point -> knots[index + 1] = point.follow(knots[index]) }
                    visitedLocations.add(knots.last())
                }
            }

            return visitedLocations.size
        }
    }
}