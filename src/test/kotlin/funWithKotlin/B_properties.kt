package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*-
# Properties
-*/

/*-
# Properties

## are functions really

^ So instead of fullName function we can define a property and what code to invoke when we access it
The Java method created here is getFullName(). Kotlin silently translates.
-*/

private object B1 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        val fullName: String
            get() {
                return firstName + " " + lastName
            }
    }

    class PersonTests {
        @Test
        fun names() {
            assertEquals("Bob TheBuilder", bob.fullName)
        }
    }
//`
}

/*-
# Properties

## can be single expressions
-*/

private object B2 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        val fullName get() = firstName + " " + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob TheBuilder", bob.fullName)
        }
    }
//`
}

/*-
# Properties

## can be indexed
-*/

private object B2a {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        operator fun get(i: Int): String = when (i) {
            0 -> firstName
            1 -> lastName
            else -> throw IndexOutOfBoundsException("No name at index $i")
        }
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob", bob[0])
            assertEquals("TheBuilder", bob[1])
        }
    }
//`
}

/*-
# Properties

## can be resolved on construction

^All the following have Java fields backing them and getter methods
-*/

private object B3 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(
        val firstName: String,
        val lastName: String
    ) {
        val fullName = firstName + " " + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob", bob.firstName)
            assertEquals("TheBuilder", bob.lastName)
            assertEquals("Bob TheBuilder", bob.fullName)
        }
    }
//`
}

/*-
# Properties

## can be constant

^In which case Kotlin won't by default create a field, just a method that returns the constant
-*/

private object B4 {
    //`
    data class Person(val firstName: String, val lastName: String) {
        val type = "Person"
    }
//`
}