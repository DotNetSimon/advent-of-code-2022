import kotlin.math.abs

class Day15 : Day {

    data class Sensor(val point: Point, val distanceToBeacon: Int, val closestBeacon: Point) {}
    data class Beacon(val point: Point, val pingedBy: MutableList<Sensor>) {
        companion object {

        }
    }

    override fun puzzleA(data: List<String>): String {
        val (sensors, beacons) = getSensorsAndBeacons(data)

        // search in row:
        val row = 2000000
        var count = 0

        // does it matter? Perhaps we should sort sensors' left max position?
        val rowBeacon = beacons.find { it.point.y == row } ?: throw Exception("Should have a beacon")
        val rowFurthestSensor = rowBeacon.pingedBy.maxByOrNull { it.distanceToBeacon }!!
        val startPosition = -2_000_000
        val endPosition = 5_000_000

        // leftmost furthest possible spot to check is if the sensor was all the way to the left of this beacon
        // rightmost furthest is twice as far (if sensor was all the way to the right, and then twice as far
        for (checkX in startPosition until endPosition) {
            if (sensors.any { it.distanceToBeacon >= Point(checkX, row).manhattanDistance(it.point) }) {
                if (beacons.none { it.point == Point(checkX, row) }) {
                    count++
                }
            }
        }

        return count.toString()
    }

    override fun puzzleB(data: List<String>): String {
        val (sensors, beacons) = getSensorsAndBeacons(data)

        val onlyOption = findScanned(sensors, beacons)

        return (onlyOption.x * 4_000_000L + onlyOption.y).toString()
    }

    private fun findScanned(sensors: List<Sensor>, beacons: List<Beacon>): Point {
        val startPosition = 0
        val endPosition = 4_000_000

        for (checkY in startPosition until endPosition) {
            println("checking line $checkY")
            var checkX = startPosition
            while (checkX < endPosition) {
                val sensorInRange =
                    sensors.find { it.distanceToBeacon >= Point(checkX, checkY).manhattanDistance(it.point) }
                if (sensorInRange == null) {
                    if (beacons.none { it.point == Point(checkX, checkY) }) {
                        return Point(checkX, checkY)
                    }
                    checkX++
                } else { // we can skip forward to the outer edge of this sensor's reach. This will give a huge performance boost.
                    checkX =
                        sensorInRange.point.x + sensorInRange.distanceToBeacon - abs(sensorInRange.point.y - checkY)+1
                }
            }
        }
        throw Exception("Couldn't find anything in this mess!")
    }

    private fun getSensorsAndBeacons(data: List<String>): Pair<MutableList<Sensor>, MutableList<Beacon>> {
        val sensors = mutableListOf<Sensor>()
        val beacons = mutableListOf<Beacon>()

        for (line in data) {
            val (sensor, beacon) = create(line)
            sensors.add(sensor)
            beacons.find { it.point == beacon.point }?.let { it.pingedBy.addAll(beacon.pingedBy) }
                ?: beacons.add(beacon)
        }
        return Pair(sensors, beacons)
    }

    fun create(input: String): Pair<Sensor, Beacon> {
        val inputs = Regex("Sensor at x=(.*), y=(.*): closest beacon is at x=(.*), y=(.*)").matchEntire(input)!!
        val sensorPoint = Point(
            inputs.groupValues[1].toInt(),
            inputs.groupValues[2].toInt()
        )
        val beaconPoint = Point(
            inputs.groupValues[3].toInt(),
            inputs.groupValues[4].toInt()
        )
        val distance = sensorPoint.manhattanDistance(beaconPoint)

        val sensor = Sensor(sensorPoint, distance, beaconPoint)
        val beacon = Beacon(beaconPoint, mutableListOf(sensor))

        return Pair(sensor, beacon)
    }

}
