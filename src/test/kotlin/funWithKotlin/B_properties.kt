package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/*-
# Properties
-*/

/*-
# Properties

## are functions really
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

## can be pre-computed
-*/

private object B3 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        val fullName = firstName + " " + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob TheBuilder", bob.fullName)
            assertEquals("Bob", bob.firstName)
            assertEquals("TheBuilder", bob.lastName)
        }
    }
//`
}