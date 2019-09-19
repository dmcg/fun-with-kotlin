package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*-
# Top-level Functions
-*/

/*-
# Functions

## can be top-level
-*/

object C1 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String)

    fun fullName(person: Person): String {
        return person.firstName + " " + person.lastName
    }

    class UtilityTests {
        @Test fun fullName() {
            assertEquals("Bob TheBuilder", fullName(bob))
        }
    }
//`
}

/*-

# Functions

## have the conveniences of methods
-*/

object C2 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String)

    fun fullName(person: Person, separator: String = " ") =
        person.firstName + separator + person.lastName

    class UtilityTests {
        @Test fun fullName() {
            assertEquals("Bob TheBuilder", fullName(bob))
            assertEquals("Bob : TheBuilder", fullName(bob, separator = " : "))
        }
    }
//`
}

