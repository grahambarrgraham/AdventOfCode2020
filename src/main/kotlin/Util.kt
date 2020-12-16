object Util {

    fun loadStringListWithoutBlanks(filename: String): List<String> =
            loadStringList(filename)
                    .filterNot(String::isEmpty)

    fun loadStringList(filename: String): List<String> {
        return javaClass.getResource(filename)
                .readText()
                .lines()
                .map(String::trim)
    }

    fun loadIntList(filename: String): List<Int> = loadStringListWithoutBlanks(filename).map(String::toInt)

    fun loadLongList(filename: String): List<Long> = loadStringListWithoutBlanks(filename).map(String::toLong)

    fun splitByBlankLine(strings: List<String>): Sequence<List<String>> = sequence {

        var i = 0;

        while (i < strings.size) {
            val list = strings.subList(i, strings.size).takeWhile { it.isNotBlank() && it.isNotEmpty() }
            yield(list)
            i += list.size + 1
        }
    }
}
