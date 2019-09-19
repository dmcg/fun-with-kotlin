package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/*-
# Infix Functions
-*/

/*-
# Infix Functions

## can be called without punctuation
-*/

object E1 {

    //`
    data class Person(val firstName: String, val lastName: String)

    infix fun String.the(occupation: String) = Person(this, "The$occupation")

    class InfixTests {
        @Test fun the() {
            assertEquals(Person("Bob", "TheBuilder"), "Bob" the "Builder")
        }
    }
//`
}

/*-
# Infix Functions

## can be methods
-*/

object E2 {
    infix fun String.the(occupation: String) = Person(this, "The$occupation")

    //`
    data class Person(val firstName: String, val lastName: String) {
        infix fun and(another: Person) = setOf(this, another)
    }

    class InfixTests {
        @Test fun and() {
            assertEquals(
                setOf(Person("Bob", "TheBuilder"), Person("Muck", "TheTruck")),
                ("Bob" the "Builder") and ("Muck" the "Truck")
            )
        }
    }
//`
}

/*-
# Infix Functions

## are the gateway to operator overloading
-*/

object E3 {
    infix fun String.the(occupation: String) = Person(this, "The$occupation")

    //`
    data class Person(val firstName: String, val lastName: String)

    infix operator fun Person.plus(another: Person) = setOf(this, another)

    class InfixTests {
        @Test fun plus() {
            assertEquals(
                setOf(Person("Bob", "TheBuilder"), Person("Muck", "TheTruck")),
                ("Bob" the "Builder") + ("Muck" the "Truck") + ("Bob" the "Builder")
            )
        }
    }
//`
}

