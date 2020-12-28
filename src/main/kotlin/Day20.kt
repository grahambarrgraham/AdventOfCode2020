/**
 * https://adventofcode.com/2020/day/20
 */
object Day20 {

    fun part1(strings: List<List<String>>): Long {
        val startingSet = readTiles(strings).toSet()
        var t1: Set<Tile> = startingSet
        var result: Tile? = null

        val done : MutableSet<Set<Tile>> = mutableSetOf()

        var counter = 1
        while(result == null) {
            val t2 = combineTiles(t1, done)
            t1 = t1 + dedupe(t2)
            t1 = t1.filter { it.ids.size >= counter }.toSet()
            if (counter < 3 || startingSet.size < 32) counter++ else counter += 9
            result = t1.find { it.ids.size == startingSet.size }
        }

        return result.corners.multiply()
    }

    private fun combineTiles(tiles: Set<Tile>, done: MutableSet<Set<Tile>>): Set<Tile> {
        val foldIndexed = tiles.foldIndexed<Tile, Set<Tile>>(emptySet()) { index, acc, tile ->
            acc + tiles
                    .drop(index + 1)
                    .filterNot { done.contains(setOf(tile, it)) }
                    .onEach { done.add(setOf(tile, it)) }
                    .flatMap { tile.validPairs(it) }
        }
        return foldIndexed
    }

    private fun readTiles(strings: List<List<String>>): List<Tile> = strings.map { readTile(it) }

    private fun readTile(strings: List<String>): Tile {
        //println("Reading $strings")
        val (id) = "Tile (.+):".toRegex().find(strings[0])!!.destructured
        val tileS = strings.drop(1)
        val top = tileS.first()
        val bottom = tileS.last()
        val left = String(tileS.map { it.first() }.toCharArray())
        val right = String(tileS.map { it.last() }.toCharArray())
        return Tile(top, left, right, bottom, Corners(id, id, id, id), listOf(id))
    }

    fun part2(strings: List<List<String>>): Long = 0

    data class Corners(val topLeft: String, val topRight: String, val bottomRight: String, val bottomLeft: String) {

        val sortedHash = listOf(topLeft, topRight, bottomLeft, bottomRight).sorted().fold("") {acc, s -> acc + s}

        fun multiply(): Long = topLeft.toLong() * topRight.toLong() * bottomLeft.toLong() * bottomRight.toLong()

        fun r1() = Corners(bottomLeft, topLeft, topRight, bottomRight)
        fun flipVertical() = Corners(bottomLeft, bottomRight, topRight, topLeft)
        fun flipHorizontal() = Corners(topRight, topLeft, bottomLeft, bottomRight)
    }

    data class Tile(val top: String, val left: String, val right: String, val bottom: String, val corners: Corners, val ids: List<String>) {

        val sideLengths = setOf(left.length, right.length, top.length, right.length)

        private var cache: Set<Tile>? = null

        fun transformations(): Set<Tile> = if (cache == null) {
            cache = findAllValidTransformations(); cache!!
        } else cache!!

        private fun flipVertical() = Tile(bottom, left.reversed(), right.reversed(), top, corners.flipVertical(), ids)
        private fun flipHorizontal() = Tile(top.reversed(), right, left, bottom.reversed(), corners.flipHorizontal(), ids)
        private fun r1() = Tile(left.reversed(), bottom, top, right.reversed(), corners.r1(), ids)

        private fun findAllValidTransformations(): Set<Tile> = rotations(flipHorizontal()) + rotations(flipVertical()) + rotations(this)

        private fun rotations(tile: Tile): Set<Tile> = setOf(tile, tile.r1())

        fun validPairs(t: Tile): Set<Tile> = dedupe(allValidPairs(t))

        fun allValidPairs(t: Tile): Set<Tile> {

            if (sideLengths.intersect(t.sideLengths).isEmpty()) {
                return emptySet()
            }

            if (ids.intersect(t.ids).isNotEmpty()) {
                return emptySet()
            }

            val toSet = this.transformations().flatMap { it1 ->
                t.transformations().flatMap { it2 ->
                    listOfNotNull(
                            topBottom(it1, it2),
                            topBottom(it2, it1),
                            leftRight(it1, it2),
                            leftRight(it2, it1))
                }
            }.toSet()

            return toSet
        }

        private fun topBottom(t1: Tile, t2: Tile): Tile? =
                if (t1.bottom == t2.top) Tile(t1.top, t1.left + t2.left, t1.right + t2.right, t2.bottom,
                        Corners(t1.corners.topLeft, t1.corners.topRight, t2.corners.bottomRight, t2.corners.bottomLeft),
                        t1.ids + t2.ids) else null

        private fun leftRight(t1: Tile, t2: Tile): Tile? =
                if (t1.right == t2.left) Tile(t1.top + t2.top, t1.left, t2.right, t1.bottom + t2.bottom,
                        Corners(t1.corners.topLeft, t2.corners.topRight, t2.corners.bottomRight, t1.corners.bottomLeft),
                        t1.ids + t2.ids) else null
    }

    fun dedupe(s: Set<Tile>) : Set<Tile> {
        return s.groupBy { it.corners.sortedHash }.map { it.value.first() }.toSet()
    }

}
