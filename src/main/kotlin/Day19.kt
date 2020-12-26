/**
 * https://adventofcode.com/2020/day/19
 */
object Day19 {

    fun eval(strings: List<List<String>>): Long {
        val candidates = strings[1]
        val matches = candidates.filter { candidate ->
            val rules = buildRules(strings)
            rules["0"]!!
                    .expansions(candidate, rules.toMutableMap())
                    .any { it == candidate }
        }
        return matches.count().toLong()
    }

    data class Rule(val id: Int, val options: List<List<String>>) {
        private var expansions: List<String>? = null
        private var recursiveDepth = 1;

        fun expansions(target: String, rules: MutableMap<String, Rule>, from: Rule? = null): List<String> {

            limitRecursiveDepthHack(from, rules)

            return if (expansions == null) {
                val result = expand(target, rules).filter { target.contains(it) }
                expansions = result
                result
            } else expansions!!
        }

        private fun limitRecursiveDepthHack(from: Rule?, rules: MutableMap<String, Rule>) {
            if (this == from) {
                recursiveDepth++
            }

            if (recursiveDepth >= 4) {
                val newRule = Rule(id, options.dropLast(1)) //grimace
                newRule.expansions = expansions
                rules[id.toString()] = newRule
            }
        }

        private fun expand(candidate: String, rules: MutableMap<String, Rule>): List<String> {
            return options.flatMap {
                allConcatenations(
                        it.map { rules[it]?.expansions(candidate, rules, this) ?: listOf(it) }
                )
            }
        }

    }

    private fun buildRules(strings: List<List<String>>) =
            strings[0].map { read(it) }.sortedBy { it.id }.associateBy { it.id.toString() }

    private fun read(it: String): Rule {

        val parts = it.split(':', '|')

        val options = parts.drop(1)
                .map { it.trim() }
                .map { it.removeSurrounding("\"") }
                .map { it.trim().split(' ') }

        return Rule(parts[0].toInt(), options.toMutableList())
    }

    private fun allConcatenations(l: List<List<String>>): List<String> {
        return l.fold(listOf("")) { a, b -> a.flatMap { d -> b.map { e -> d + e } } }
    }

}
