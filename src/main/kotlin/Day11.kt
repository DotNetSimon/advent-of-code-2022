import java.math.BigInteger
import java.util.*

class Day11 {

    data class Monkey(
        val items: Queue<BigInteger>,
        val increaseWorry: (BigInteger) -> BigInteger,
        val testValue: BigInteger,
        val throwToMonkey: Pair<Int, Int>,
        private var timesItemInspected: Int = 0
    ) {
        val testCommonMultiplier = 13*11*2*5*7*3*19*17 // all the test divisors combined.

        fun inspectAndThrowItemsToOtherMonkeys(monkeys: List<Monkey>, boredomFactor: Int) {
            while (items.size > 0) {
                var item = items.remove()
                timesItemInspected++
                item = increaseWorry(item)
                item = boredWith(item, boredomFactor)
                if (testWorry(item)) {
                    monkeys[throwToMonkey.first].items.add(item)
                } else {
                    monkeys[throwToMonkey.second].items.add(item)
                }
            }
        }

        private fun boredWith(item: BigInteger, boredomFactor: Int): BigInteger {
            val multipliedDividers = testCommonMultiplier.toBigInteger()
            if (boredomFactor == 0) {
                return if (item > multipliedDividers) {
                    item.mod(multipliedDividers)
                } else {
                    return item
                }
            }
            return item.divide(boredomFactor.toBigInteger())
        }

        override fun toString(): String {
            return "${items} inspections: ${timesItemInspected}"
        }
        fun getTimesItemInspected() = timesItemInspected
        private fun testWorry(item: BigInteger) = item.mod(testValue) == BigInteger.ZERO
    }

    companion object {

        fun puzzleA(data: List<String>): Int {
            var monkeys = readMonkeys(data)

            for (round in 0 until 20) {
                for (monkey in monkeys) {
                    monkey.inspectAndThrowItemsToOtherMonkeys(monkeys, 3)
                }
            }

            val mostActiveMonkeys = monkeys.sortedByDescending { it.getTimesItemInspected() }.take(2)

            return mostActiveMonkeys[0].getTimesItemInspected()*mostActiveMonkeys[1].getTimesItemInspected()

        }

        private fun readMonkeys(data: List<String>): MutableList<Monkey> {
            var monkeys = mutableListOf<Monkey>()
            var monkeyId = 0
            for (block in data.chunked(6)) {
                val items = block[1].substringAfter(": ").split(", ").map { it.toBigInteger() }
                val worryIncreaseFunction = parseExpression(block[2])
                val testValue = block[3].filter { it.isDigit() }.toBigInteger()
                val trueMonkey = block[4].filter { it.isDigit() }.toInt()
                val falseMonkey = block[5].filter { it.isDigit() }.toInt()
                monkeys.add(Monkey(ArrayDeque(items), worryIncreaseFunction, testValue, Pair(trueMonkey, falseMonkey)))
                monkeyId++
            }
            return monkeys
        }

        private fun parseExpression(str: String): (BigInteger) -> BigInteger {
            val parts = str.split(" ")
            val operator = parts[6]

            if (parts[7] == "old") {
                return { i -> i * i}
            }

            val value = parts[7].toBigInteger()
            return when (operator) {
                "+" -> { i -> i + value }
                "*" -> { i -> i * value }
                else -> throw IllegalArgumentException("did not recognize operator")
            }
        }


        fun puzzleB(data: List<String>): Long {
            var monkeys = readMonkeys(data)

            for (round in 0 until 10000) {
                for (monkey in monkeys) {
                    monkey.inspectAndThrowItemsToOtherMonkeys(monkeys, 0)
                }
                println("R: $round  ----------------")
                monkeys.forEach{println(it)}
            }

            val mostActiveMonkeys = monkeys.sortedByDescending { it.getTimesItemInspected() }.take(2)

            return mostActiveMonkeys[0].getTimesItemInspected().toLong()*mostActiveMonkeys[1].getTimesItemInspected()

        }
    }
}