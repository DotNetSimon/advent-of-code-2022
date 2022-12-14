import Day13.Companion.parseSignal

data class Signal(val value: Int?, val signal: MutableList<Signal>?) {

    /**
     * compare this signal with another signal. Negative value means this one is smaller.
     */
    fun compare(right: Signal?): Int {
        if (right == null) {
            return -1
        }

        if ((value == null) && right.value != null) {
            return listCompare(signal, right.convertValueToList())
        }
        if (value != null && right.value == null) {
            return listCompare(this.convertValueToList(), right.signal)

        }
        if (value != null && right.value != null) {
            return value - right.value
        }

        if (value == null && right.value == null) {
            return listCompare(signal, right.signal)
        }

        return 0
    }

    private fun listCompare(left: List<Signal>?, right: List<Signal>?): Int {
        if (left == null && right != null) {
            return -1
        }
        if (left != null && right == null) {
            return 1
        }

        if (left != null && right != null) {
            for (i in left.indices) {
                if (i > right.lastIndex) {
                    return 1
                }
                val rightSignal = right?.get(i)
                val diff = left[i].compare(rightSignal)
                if (diff != 0) {
                    return diff
                }
            }
            if (left.size < right.size) {
                return -1
            }
        }
        return 0
    }

    fun convertValueToList(): List<Signal> {
        return listOf(Signal(value, null))
    }
}

class Day13 : Day {

    // [1]
    // 1

    // [[1,1[[1],2],3],4]
    // { } 1,1 { } 1],2],3],4]
    companion object {
        fun parseSignal(str: String, signal: Signal): Pair<String, Signal?> {

            if (str.isEmpty()) {
                return Pair("", signal)
            }

            when (str[0]) {
                '[' -> {
                    val newSignal = Signal(null, mutableListOf())
                    val (newStr, _) = parseSignal(
                        str.slice(1 until str.length),
                        newSignal
                    )
                    signal.signal?.add(newSignal)
                    return parseSignal(newStr, signal)
                }

                ']' -> return Pair(str.slice(1 until str.length), null)
                else -> {
                    val parseNumbers = str.takeWhile { it != ']' && it != '[' }
                    signal.signal?.addAll(
                        parseNumbers.split(",")
                            .filter { it.isNotEmpty() }
                            .map { Signal(it.toInt(), null) }
                    )
                    var remainingStr = str.slice(parseNumbers.length until str.length)
                    while (remainingStr.isNotEmpty()) {
                        var (newStr, newSignal) = parseSignal(remainingStr, signal)
                        remainingStr = newStr
                        if (newSignal == null) {
                            return Pair(remainingStr, null)
                        }
                    }
                    return parseSignal(remainingStr, signal)
                }
            }
        }
    }

    override fun puzzleA(data: List<String>): String {
        val indexes = mutableListOf<Int>()
        var index = 1
        for (i in data.indices step 2) {
            val (_, left) = parseSignal(data[i], Signal(null, mutableListOf()))
            val (_, right) = parseSignal(data[i + 1], Signal(null, mutableListOf()))

            val comp = left?.compare(right) ?: 0
            if (comp <= 0) {
                indexes.add(index)
            }
            index++
        }
        return indexes.sumOf { it }.toString()
    }

    override fun puzzleB(data: List<String>): String {
        val packets = mutableListOf<Signal>()
        val divider1 = Signal(null, mutableListOf(Signal(2,null)))
        val divider2 = Signal(null, mutableListOf(Signal(6,null)))

        packets.add(divider1)
        packets.add(divider2)

        for (line in data) {
            val (_, packet) = parseSignal(line, Signal(null, mutableListOf()))
            packets.add(packet!!)
        }
        val sortedPackets = packets.sortedWith { a: Signal, b: Signal -> a.compare(b) }

        val pos1 = sortedPackets.indexOf(divider1)+1
        val pos2 = sortedPackets.indexOf(divider2)+1

        return (pos1*pos2).toString()
    }


}