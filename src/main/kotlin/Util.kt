object Util {

    fun loadStringList(filename: String): List<String> =
            javaClass.getResource(filename)
                    .readText()
                    .lines()
                    .map(String::trim)
                    .filterNot(String::isEmpty)

    fun loadIntList(filename: String): List<Int> = loadStringList(filename).map(String::toInt)
}
