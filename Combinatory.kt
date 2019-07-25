fun <T : Any> permutationsWithRepetition(n: Int, elements: List<T>): Sequence<List<T>> {
    return sequence {
        val s = elements.size
        val c = IntArray(n)
        while (c[n - 1] < n) {
            yield(c.map { elements[it] })
            c[0]++
            for (idx in 0 until n) {
                if (c[idx] == s) {
                    if (idx == n - 1) {
                        return@sequence //Done
                    }
                    c[idx] = 0
                    if (idx < n - 1) {
                        c[idx + 1]++
                    }
                } else {
                    break
                }
            }
        }
    }
}

/**
 * Groups of [n] size, with all possible combination amounts of [elements].
 */
fun <T> combinationsWithRepetition(n: Int, elements: List<T>): Sequence<List<T>> {
    return sequence {
        val c = IntArray(n) { 0 }
        var i = 0
        while (i < n) {
            if (c[i] == elements.size - 1) {
                i++
            } else {
                yield(c.map { elements[it] })
                c[i]++
            }
        }
        yield(c.map { elements[it] })
    }
}

/**
 * All combinations of size n.
 */
fun <T> combinations(n: Int, elements: List<T>): Sequence<List<T>> {
    require(n <= elements.size)
    return sequence {
        combinationsWithRepetition(elements.size, listOf(true, false))
    }
}
