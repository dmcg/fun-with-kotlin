package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.text.RegexOption.IGNORE_CASE

/*-
# Higher-Order Functions
-*/


data class Person(val firstName: String, val lastName: String)
val bob = Person("Bob", "TheBuilder")
val vowels = "[aeiou]".toRegex(IGNORE_CASE)


/*-
# Higher-Order Functions

## returning functions as values
-*/

object G1 {
    //`
    fun occupier(occupation: String): (String) -> Person {
        return fun(firstName: String) = Person(firstName, "The$occupation")
    }

    class OccupierTests {
        @Test fun test() {
            val builderBuilder = occupier("Builder")
            assertEquals(Person("Bob", "TheBuilder"), builderBuilder("Bob"))
            assertEquals(Person("Brian", "TheBuilder"), builderBuilder("Brian"))
        }
    }
//`
}

/*-
# Higher-Order Functions

## taking functions as parameters

^ Note the use of a function reference here. We will see other ways of implementing functions later
-*/

object G2 {
    //`
    fun obfuscate(person: Person, f: (String) -> String) =
        Person(f(person.firstName), f(person.lastName))

    class ObfuscationTests {
        @Test fun test() {
            fun translator(s: String): String = s.replace(vowels, "*")
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                obfuscate(bob, ::translator)
            )
        }
    }
//`
}

/*-
# Higher-Order Functions

## lambda expressions can replace functions
-*/

object G3 {
    //`
    fun obfuscate(person: Person, f: (String) -> String) =
        Person(f(person.firstName), f(person.lastName))

    class ObfuscationTests {
        @Test fun test() {
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                obfuscate(bob, { s: String -> s.replace(vowels, "*") })
            )
        }
    }
//`
}

/*-
# Higher-Order Functions

## 'it' can be used for a single lambda parameter
-*/

object G4 {
    //`
    fun obfuscate(person: Person, f: (String) -> String) =
        Person(f(person.firstName), f(person.lastName))

    class ObfuscationTests {
        @Test fun test() {
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                obfuscate(bob, { it.replace(vowels, "*") })
            )
        }
    }
//`
}

/*-
# Higher-Order Functions

## lambdas can be moved outside the parens
-*/

object G5 {
    //`
    fun obfuscate(person: Person, f: (String) -> String) =
        Person(f(person.firstName), f(person.lastName))

    class ObfuscationTests {
        @Test fun test() {
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                obfuscate(bob) { it.replace(vowels, "*") }
            )
        }
    }
//`
}

/*-
# Higher-Order Functions

## and now obfuscate made an extension function
-*/

object G6 {
    //`
    fun Person.obfuscatedBy(f: (String) -> String) =
        Person(f(firstName), f(lastName))

    class ObfuscationTests {
        @Test fun test() {
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                bob.obfuscatedBy { it.replace(vowels, "*") }
            )
        }
    }
//`
}

/*-
# Higher-Order Functions

## or a little higher-order infix action
-*/

object G7 {
    //`
    fun Person.obfuscatedBy(f: (String) -> String) =
        Person(f(firstName), f(lastName))

    infix fun String.replacing(regex: Regex): (String) -> String = {
        it.replace(regex, this)
    }

    class ObfuscationTests {
        @Test fun test() {
            assertEquals(
                Person("B*b", "Th*B**ld*r"),
                bob.obfuscatedBy("*" replacing vowels)
            )
        }
    }
//`
}