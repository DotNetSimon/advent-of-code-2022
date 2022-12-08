class Day8 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            var score = 0

            val width = data[0].length
            val height = data.size
            val forest = Forest(data.map { it.map { it.digitToInt() } }, width, height)

            // add all outside trees:
            score += 2 * width // both top and bottom row, include corner
            score += 2 * (height-2) // sides without corner

            for (h in 1 until height - 1) {
                for (w in 1 until width - 1) {
                    if (forest.isTreeVisible(w, h)) {
                        score += 1
                    }
                }
            }

            return score
        }


        fun puzzleB(data: List<String>): Int {
            var maxScore = 0

            val width = data[0].length
            val height = data.size
            val forest = Forest(data.map { it.map { it.digitToInt() } }, width, height)


            for (h in 1 until height - 1) {
                for (w in 1 until width - 1) {
                    val score = forest.scenicScore(w, h)
                    if (score > maxScore) {
                        maxScore = score
                    }
                }
            }

            return maxScore
        }
    }

    class Forest(val trees: List<List<Int>>, val width: Int, val height: Int) {
        /**
         * check if the tree is visible from outside:
         * has to have equal or higher trees in each cardinal direction to be hidden.
         */
        fun isTreeVisible(w: Int, h: Int): Boolean {
            val treeheight = trees[h][w]
            var hiddenFromDirections = 0
            // horizontal left :
            for (x in 0 until w) {
                if (trees[h][x] >= treeheight) {
                    hiddenFromDirections += 1
                    break
                }
            }
            // horizontal right
            for (x in w+1 until width) {
                if (trees[h][x] >= treeheight) {
                    hiddenFromDirections += 1
                    break
                }
            }

            //vertical up
            for (y in 0 until h) {
                if (trees[y][w] >= treeheight) {
                    hiddenFromDirections += 1
                    break
                }
            }
            // vertical down
            for (y in h+1 until height) {
                if (trees[y][w] >= treeheight) {
                    hiddenFromDirections += 1
                    break
                }
            }

            // tree is visible from outside if one of the directions does not have a tree equal or higher.
            val isVisible = hiddenFromDirections < 4

            //println("tree $treeheight at $w,$h isvisible: $isVisible")
            return isVisible
        }

        fun scenicScore(w: Int, h: Int): Int {
            val treeheight = trees[h][w]
            var viewDistanceDirection = mutableMapOf("left" to 0, "right" to 0, "up" to 0, "down" to 0)
            // horizontal left :
            for (x in w-1 downTo 0 ) {
                viewDistanceDirection["left"] = w-x
                if (trees[h][x] >= treeheight) {
                    break
                }
            }
            // horizontal right
            for (x in w+1 until width) {
                viewDistanceDirection["right"] = x-w
                if (trees[h][x] >= treeheight) {
                    break
                }
            }

            //vertical up
            for (y in h-1 downTo 0) {
                viewDistanceDirection["up"] = h-y
                if (trees[y][w] >= treeheight) {
                    break
                }
            }
            // vertical down
            for (y in h+1 until height) {
                viewDistanceDirection["down"] = y-h
                if (trees[y][w] >= treeheight) {
                    break
                }
            }

            println("tree $treeheight at $w,$h viewDistance: $viewDistanceDirection")
            val result = viewDistanceDirection.map{it.value }.fold(1) {total, element -> total * element }
            return result
        }
    }
}