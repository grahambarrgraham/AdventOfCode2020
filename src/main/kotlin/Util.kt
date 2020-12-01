object Util {

    fun loadIntList(filename: String): List<Int> =
            javaClass.getResource(filename)
                    .readText()
                    .lines()
                    .map(String::trim)
                    .filterNot(String::isEmpty)
                    .map(String::toInt)
}
