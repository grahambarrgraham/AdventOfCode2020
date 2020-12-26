import java.lang.StringBuilder

/**
 * https://adventofcode.com/2020/day/20
 */
object Day20 {

    fun part1(strings: List<List<String>>): Long {
        var tiles: Set<Tile> = readTiles(strings).toSet()

        while (tiles.size > 1) {
            tiles = combineTiles(tiles)
            println("Combined tiles : ${tiles.count()} : $tiles")
        }

        return tiles.first().corners.multiply()
    }

    private fun combineTiles(tiles: Set<Tile>): Set<Tile> {
        val foldIndexed = tiles.foldIndexed<Tile, Set<Tile>>(emptySet()) { index, acc, tile ->
            acc + tiles
                    .drop(index + 1)
                    .flatMap { tile.validPairs(it) }
        }
        return foldIndexed
    }

    private fun readTiles(strings: List<List<String>>): List<Tile> = strings.map { readTile(it) }

    private fun readTile(strings: List<String>): Tile {
        val (id) = "Tile (.+):".toRegex().find(strings[0])!!.destructured
        val tileS = strings.drop(1)
        val top = tileS.first()
        val bottom = tileS.last()
        val left = String(tileS.map { it.first() }.toCharArray())
        val right = String(tileS.map { it.last() }.toCharArray())
        return Tile(top, left, right, bottom, Corners(id, id, id, id))
    }

    fun part2(strings: List<List<String>>): Long = 0

    fun corners(topLeft: String, topRight: String, bottomRight: String, bottomLeft: String): Corners = Corners(topLeft, topRight, bottomRight, bottomLeft)

    fun tile(top: String, left: String, right: String, bottom: String, corners: Corners, desc: String): Tile { val tile = Tile(top, left, right, bottom, corners); tile.desc = desc; return tile }

    data class Corners(val topLeft: String, val topRight: String, val bottomRight: String, val bottomLeft: String) {

        fun multiply(): Long = topLeft.toLong() * topRight.toLong() * bottomLeft.toLong() * bottomLeft.toLong()

        fun r1() = corners(bottomLeft, topLeft, topRight, bottomRight)
        fun r2() = corners(bottomRight, bottomLeft, topLeft, topRight)
        fun r3() = Corners(topRight, bottomRight, bottomLeft, topLeft)
        fun flipVertical() = corners(bottomLeft, bottomRight, topRight, topLeft)
        fun flipHorizontal() = corners(topRight, topLeft, bottomLeft, bottomRight)
    }

    data class Tile(val top: String, val left: String, val right: String, val bottom: String, val corners: Corners) {

        var desc: String? = null

        override fun toString(): String {

            var bob = StringBuilder()
            bob.append("Tile :: $corners").append("\n")
            bob.append(top).append("\n")
            (0 until left.length).forEach {
                bob.append(left[it]).append(" ".repeat(top.length - 2)).append(right[it]).append("\n")
            }
            bob.append(bottom).append("\n")
            return bob.toString()
        }

        private var cache: Set<Tile>? = null

        fun transformations(): Set<Tile> = if (cache == null) {
            cache = findAllValidTransformations(); cache!!
        } else cache!!

        private fun flipVertical() = tile(bottom, left.reversed(), right.reversed(), top, corners.flipVertical(), "${desc}_fv")
        private fun flipHorizontal() = tile(top.reversed(), right, left, bottom.reversed(), corners.flipHorizontal(), "${desc}_fh")
        private fun r3() = tile(right, top.reversed(), bottom.reversed(), left, corners.r3(), "${desc}_r3")
        private fun r2() = tile(bottom.reversed(), right.reversed(), left.reversed(), top.reversed(), corners.r2(), "${desc}_r2")
        private fun r1() = tile(left.reversed(), bottom, top, right.reversed(), corners.r1(), "${desc}_r1")

        private fun findAllValidTransformations(): Set<Tile> = rotations(flipHorizontal()) + rotations(flipVertical()) + rotations(this)

        private fun rotations(tile: Tile): Set<Tile> = setOf(tile, tile.r1(), tile.r2(), tile.r3())

        fun validPairs(t: Tile): Set<Tile> = dedupe(allValidPairs(t))

        fun allValidPairs(t: Tile): Set<Tile> {
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
                        Corners(t1.corners.topLeft, t1.corners.topRight, t2.corners.bottomRight, t2.corners.bottomLeft)) else null

        private fun leftRight(t1: Tile, t2: Tile): Tile? =
                if (t1.right == t2.left) Tile(t1.top + t2.top, t1.left, t2.right, t1.bottom + t2.bottom,
                        Corners(t1.corners.topLeft, t2.corners.topRight, t2.corners.bottomRight, t1.corners.bottomLeft)) else null
    }

    fun dedupe(s: Set<Tile>) : Set<Tile> {
        return s.fold(emptySet()) { acc, tile -> if (tile.transformations().intersect(acc).isEmpty()) acc + tile else acc }
    }


    @JvmStatic
    fun main(args: Array<String>) {
        var t = Tile("abc", "ahg", "cde", "gfe", Corners("1", "2", "3", "4"))
        var t2 = Tile("abc", "azz", "czz", "cde", Corners("11", "22", "33", "44"))

        var tt = t.allValidPairs(t2)
        val ttt = dedupe(tt)

        println("Tile $t")
        println("Tile $t2")
        println("Tile $ttt")
        println("Count ${ttt.count()}")
    }

}
