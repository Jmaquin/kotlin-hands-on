package fr.xebia.xke

import java.util.*
import kotlin.collections.ArrayList

/**
 * Task 2.1. Data class is a class to do nothing but hold data
 *
 * The same concepts exist in other languages: Lombock in Java, Case Classes in Scala, etc
 *
 * The compiler automatically derives:
 * - equals()/hashCode()
 * - toString()
 * - componentN() functions corresponding to the properties in their order of declaration
 * - copy()
 *
 * They will not be generated if explicitly defined
 */

// TODO("2.1.1. Create a class Director with name: String, bio: String and filmography: List<String>")
// Filmography should have a default value of empty list
data class SpecialDirector(val name: String, val bio: String, val filmography: List<String> = emptyList())

fun filmographyContaining(dir: SpecialDirector, keyword: String): List<String> {
    //TODO("2.1.2. Deconstruct the director and add his name to every film containing the keyword specified")
    val (name, _, filmography) = dir
    return filmography
        .filter { it.contains(keyword) }
        .map { "$name - $it" }
}


/**
 * Task 2.2. Companion objects
 *
 * The 'static' keyword is not present in kotlin so we need to create our static code inside a companion object
 * construct
 *
 * companion object {
 *   // static functions goes here
 * }
 *
 * The init blocks lets you write code inside your class constructor
 *
 * init {
 *   print("This is the constructor")
 * }
 *
 */
class CsvFilmLoader private constructor(fileName: String, callback: (String) -> Unit) {
    // TODO("2.2.1. Change the value of the actualPath variable to capitals")
    var actualPath: String = fileName.toUpperCase()

    // TODO("2.2.2. insert init block here; don't forget to call the callback")
    init {
        callback("Contents of $actualPath")
    }

    // TODO("2.2.3. Insert companion object here to create a new instance of the class")
    companion object {
        fun build(fileName: String, callback: (String) -> Unit): CsvFilmLoader =
            CsvFilmLoader(fileName, callback)
    }
}

/**
 * Task 2.3. Sealed class
 *
 * Sealed class are used for representing restricted class hierarchies
 *
 * A sealed class can have subclasses, but all of them must be declared in the same file as the sealed class itself
 */
sealed class Genre(val desc: String)

object Action : Genre("Action")

object Drama : Genre("Drama")

object Horror : Genre("Horror")

object Thriller : Genre("Thriller")

object Crime : Genre("Crime")

object War : Genre("War")

object Romance : Genre("Romance")

object Mistery : Genre("Mistery")

object Comedy : Genre("Comedy")

object SciFi : Genre("Sci-Fi")


sealed class Director(val name: String)

object Kurosawa : Director("Kurosawa")

object Hitchcock : Director("Hitchcock")

object Spielberg : Director("Spielberg")

object Kubrick : Director("Kubrick")

object RandomDirector : Director("does_not_matter")

/**
 * Task 2.4. getters/setters
 *
 */


/**
 * Task 2.5. generics (site-variance)
 */
fun List<String>.splitWordsAndLines(): Pair<List<String>, List<String>> {
    // TODO("2.5.1. uncomment this line")
    return this.partitionTo(ArrayList<String>(), ArrayList<String>()) { s -> !s.contains(" ") }
}

fun Set<Char>.splitLettersAndOthers(): Pair<Set<Char>, Set<Char>> {
    // TODO("2.5.1. uncomment this line")
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
}

/*TODO(
        """
        Task 2.5.2.
        Write a 'partitionTo' extension function to the Collection class
        This function should split a collection into two according to a predicate
        The signature of the 'toCollection()' function from the standard library may help you.
    """)*/
/**
 *
 *  While in an extension function on a collection, you could iterate the list as follows
 *  for (e in this) {
 *   print("this is an element: $e")
 * }
 */
fun <A, B : MutableCollection<A>> Collection<A>.partitionTo(first: B, second: B, predicate: (A) -> Boolean): Pair<B, B> {
    for (e in this) {
        if (predicate(e)) {
            first.add(e)
        } else {
            second.add(e)
        }
    }
    return Pair(first, second)
}


/**
 * Task 2.6. type alias
 * TODO
 */


/**
 * Task 2.7. collections & structures map, pair
 */
data class Film(val name: String,
                val releaseYear: Int,
                val director: Director,
                val type: List<Genre>,
                val price: Double = 0.0)

/**
 * GetFilmsMadeBy should return films directed by the given director
 */
fun getFilmsMadeBy(director: Director, films: List<Film>): List<Film> {
    //TODO("2.7.1. Filter films of this director")
    return films
        .filter { it.director == director }
}

/**
 * filterFilmsUsingFilter should return films directed by the given director (using given filter)
 */
fun filterFilmsUsingFilter(films: List<Film>, withCustomFilter: (Film) -> Boolean): List<Film> {
    //TODO("2.7.2. filter using high order function (function as parameter)")
    return films
        .filter(withCustomFilter)
}

/**
 * sumPricesWithFolding should use List#foldLeft to return the given films prices sum
 */
fun sumPricesWithFolding(films: List<Film>): Double {
    // TODO("2.7.x. map the film to its price, then reduce the sum")
    return films
        .map { it.price }
        .reduce { total, price -> total + price }
}
