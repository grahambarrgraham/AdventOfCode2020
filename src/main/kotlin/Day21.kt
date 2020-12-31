/**
 * https://adventofcode.com/2020/day/21
 */
object Day21 {

    fun part1(strings: List<String>): Long {
        val (allIngredients, allergenMap) = findAllergenicIngredients(strings)
        val allAllergens = allergenMap.values.flatten()
        return allIngredients.filterNot { allAllergens.contains(it) }.count().toLong()
    }

    private fun findAllergenicIngredients(strings: List<String>): Pair<List<String>, MutableMap<String, MutableSet<String>>> {
        var foods = readFoods(strings).toSet()
        val allergenMap = emptyMap<String, MutableSet<String>>().toMutableMap()
        val allergens = foods.flatMap { it.allergens }.toSet()
        val allIngredients = foods.flatMap { it.ingredients }

        for (allergen in allergens) {
            for (food in foods) {
                if (food.allergens.contains(allergen)) {
                    if (allergenMap.containsKey(allergen)) {
                        allergenMap[allergen] = allergenMap[allergen]!!.intersect(food.ingredients).toMutableSet()
                    } else {
                        allergenMap[allergen] = food.ingredients.toMutableSet()
                    }
                }
            }
        }

        var allergenIdentified = mutableSetOf<String>()
        while (allergenMap.values.map { it.size }.max()!! > 1) {
            allergenMap.filter { it.value.size == 1 }.forEach { allergenIdentified.add(it.value.first()) }
            allergenMap.filter { it.value.size > 1 }.forEach { it.value.removeAll(allergenIdentified) }
        }

        return Pair(allIngredients, allergenMap)
    }

    fun part2(strings: List<String>): String {
        val allergenMap = findAllergenicIngredients(strings).second
        return allergenMap
                .map { Pair(it.key, it.value.first()) }
                .sortedBy { it.first }
                .map { it.second }
                .drop(1)
                .fold(allergenMap.map { Pair(it.key, it.value.first()) }
                        .sortedBy { it.first }
                        .map { it.second }.first()) { acc, s -> "$acc,$s" }
    }


    data class Food(val ingredients: Set<String>, val allergens: Set<String>)

    private fun readFoods(strings: List<String>) = strings.map { read(it) }

    private fun read(it: String): Food {
        val (aS, bS) = "(.+) \\(contains (.+)\\)".toRegex().find(it)!!.destructured
        return Food(aS.trim().split(' ').toSet(), bS.trim().split(", ").sorted().toSet())
    }

}
