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
}
