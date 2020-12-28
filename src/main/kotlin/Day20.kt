import kotlin.random.Random

/**
 * https://adventofcode.com/2020/day/20
 */
object Day20 {

    fun part1(strings: List<List<String>>): Long {
        return arrangeTiles(strings)!!.corners.multiply()
    }

    fun part2(strings: List<List<String>>): Long {

        var image: List<String> = arrangeImage(arrangeTiles(strings)!!, loadImages(strings))

        var seahorses = 0
        while(true) {
            seahorses = countSeaHorses(image)
            if (seahorses > 0) break
            image = applyOperations(image, listOf(Op.values()[Random.nextInt(3)]))
        }

        val hashes = image.map { it.count { it == '#' } }.sum()
        return (hashes - (15 * seahorses)).toLong()
    }

    private fun countSeaHorses(image: List<String>): Int {
        val toRegex = "#....##....##....###".toRegex()
        return image
                .mapIndexed { index, it ->
                    toRegex.findAll(it)
                            .map { isSeahorse(index, it, image) }
                            .filter { it }.toList()
                }.flatten().filter { it }.count()
    }

    private fun isSeahorse(line: Int, match: MatchResult, image: List<String>) =
            !match.value.isBlank() &&
            (line in 1..image.size - 2) &&
                    listOf(1, 4, 7, 10, 13, 16)
                            .map { image[line + 1][match.range.first + it] }
                            .filter { it == '#' }.count() == 6 &&
                    image[line - 1][match.range.first + 18] == '#'

    private fun arrangeImage(tile: Tile, images: Map<String, List<String>>): List<String> {
        val image =
                if (tile.parents == null) {
                    val i = images[tile.ids.first()]!!
                    i
                } else {
                    arrangeImage(tile.parents.t1, images) + arrangeImage(tile.parents.t2, images)
                }

        return applyOperations(image, tile.ops)
    }

    private fun loadImages(strings: List<List<String>>): Map<String, List<String>> =
            strings
                    .map { loadImage(it) }
                    .associate { it.first to it.second }

    private fun loadImage(strings: List<String>): Pair<String, List<String>> {
        val (id) = "Tile (.+):".toRegex().find(strings[0])!!.destructured
        val tileS = strings.drop(2).dropLast(1).map { it.drop(1).dropLast(1) }
        return Pair(id, tileS)
    }

    private fun arrangeTiles(strings: List<List<String>>): Tile? {
        val startingSet = readTiles(strings).toSet()
        var t1: List<Tile> = startingSet.toList()
        var result: Tile? = null

        //this is a crutch to stop pairs of tiles that have already been compared, being compared again
        val done: MutableSet<Set<Tile>> = mutableSetOf()

        var counter = 1
        while (result == null) {
            val t2 = combineTiles(t1, done)
            t1 = dedupe(t2) + t1
            t1 = t1.filter { it.ids.size >= counter }
            //this is heuristic (hack) to aggressively exclude smaller tiles on subsequent iterations
            //this reduces the search space as the total number of tiles increases on each iteration
            //this is hacky way of implementing prioritisation to larger tiles pairs
            if (counter < 3 || startingSet.size < 32) counter++ else counter += 9
            result = t1.find { it.ids.size == startingSet.size }
        }
        return result
    }

    private fun combineTiles(tiles: List<Tile>, done: MutableSet<Set<Tile>>): List<Tile> {

        //this generates the full set of valid combined tiles from all combinations of the input tiles
        //It is entirely brute force, it doesn't apply prioritisation of tile pairs which are good
        //candidates nor does it complete eagerly when the full solution is found

        return tiles.foldIndexed(emptyList()) { index, acc, tile ->
            acc + tiles
                    .drop(index + 1)
                    .filterNot { done.contains(setOf(tile, it)) }
                    .onEach { done.add(setOf(tile, it)) }
                    .filter { tile.isValidPair(it) }
                    .map { tile.allValidPairs(it) }
                    .flatMap { dedupe(it) }
        }
    }

    private fun readTiles(strings: List<List<String>>): List<Tile> = strings.map { readTile(it) }

    private fun readTile(strings: List<String>): Tile {
        val (id) = "Tile (.+):".toRegex().find(strings[0])!!.destructured
        val tileS = strings.drop(1)
        val top = tileS.first()
        val bottom = tileS.last()
        val left = String(tileS.map { it.first() }.toCharArray())
        val right = String(tileS.map { it.last() }.toCharArray())
        return Tile(top, left, right, bottom, Corners(id, id, id, id), listOf(id))
    }

    data class Corners(val topLeft: String, val topRight: String, val bottomRight: String, val bottomLeft: String) {

        val sortedHash = listOf(topLeft, topRight, bottomLeft, bottomRight).sorted().fold("") { acc, s -> acc + s }

        fun multiply(): Long = topLeft.toLong() * topRight.toLong() * bottomLeft.toLong() * bottomRight.toLong()

        fun r1() = Corners(bottomLeft, topLeft, topRight, bottomRight)
        fun flipVertical() = Corners(bottomLeft, bottomRight, topRight, topLeft)
        fun flipHorizontal() = Corners(topRight, topLeft, bottomLeft, bottomRight)
    }

    enum class Op { Rotate, FlipVertical, FlipHorizontal }

    fun applyOperations(image: List<String>, list: List<Op>): List<String> {
        var result = image
        list.forEach {
            when (it) {
                Op.FlipVertical -> result = result.reversed()
                Op.FlipHorizontal -> result = result.map { it.reversed() }
                Op.Rotate -> result = transpose(result.map { it.toCharArray().toList() }).map { String(it.toCharArray()).reversed() }
            }
        }
        return result
    }

    data class Tile(val top: String, val left: String, val right: String, val bottom:
    String, val corners: Corners, val ids: List<String>, val parents: Parents? = null,
                    val ops: List<Op> = emptyList()) {

        private val sideLengths = setOf(left.length, right.length, top.length, right.length)

        private var cache: Set<Tile>? = null

        fun transformations(): Set<Tile> = if (cache == null) {
            cache = findAllValidTransformations(); cache!!
        } else cache!!

        private fun flipVertical() = Tile(bottom, left.reversed(), right.reversed(), top, corners.flipVertical(), ids, parents, ops + Op.FlipVertical)
        private fun flipHorizontal() = Tile(top.reversed(), right, left, bottom.reversed(), corners.flipHorizontal(), ids, parents, ops + Op.FlipHorizontal)
        private fun r1() = Tile(left.reversed(), bottom, top, right.reversed(), corners.r1(), ids, parents, ops + Op.Rotate)

        private fun findAllValidTransformations(): Set<Tile> = rotations(flipHorizontal()) + rotations(flipVertical()) + rotations(this)

        private fun rotations(tile: Tile): Set<Tile> = setOf(tile, tile.r1())

        fun isValidPair(t: Tile): Boolean =
                (sideLengths.intersect(t.sideLengths).isNotEmpty()) && (ids.intersect(t.ids).isEmpty())

        //this performs more comparisons that is necessary (a max of 32 are required), so a dedupe is required
        fun allValidPairs(t: Tile): List<Tile> =
                transformations().flatMap { it1 ->
                    t.transformations().mapNotNull { it2 -> topBottom(it2, it1) }
                }

        private fun topBottom(t1: Tile, t2: Tile): Tile? =
                if (t1.bottom == t2.top) Tile(t1.top, t1.left + t2.left, t1.right + t2.right, t2.bottom,
                        Corners(t1.corners.topLeft, t1.corners.topRight, t2.corners.bottomRight, t2.corners.bottomLeft),
                        t1.ids + t2.ids, Parents(t1, t2)) else null
    }

    //this is a crutch (hack) to remove duplicate tile pairs that are produced unnecessarily
    fun dedupe(s: List<Tile>): List<Tile> {
        return s.groupBy { it.corners.sortedHash }.map { it.value.first() }
    }

    data class Parents(val t1: Tile, val t2: Tile)

    private fun <E> transpose(xs: List<List<E>>): List<List<E>> {
        // Helpers
        fun <E> List<E>.head(): E = this.first()

        fun <E> List<E>.tail(): List<E> = this.takeLast(this.size - 1)
        fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)

        xs.filter { it.isNotEmpty() }.let { ys ->
            return when (ys.isNotEmpty()) {
                true -> ys.map { it.head() }.append(transpose(ys.map { it.tail() }))
                else -> emptyList()
            }
        }
    }
}
