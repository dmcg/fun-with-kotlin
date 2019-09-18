package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/*-

# Methods
-*/

object A1 {
    //`
    data class Person(val firstName: String, val lastName: String) {
        fun fullName(): String {
            return firstName + " " + lastName
        }
    }

    class PersonTests {
        val bob = Person("Bob", "TheBuilder")
        @Test fun names() {
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
---

# Methods

## can be a single expression
-*/

object A2 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        fun fullName(): String = firstName + " " + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
---

# Methods

## can have an explicit return type
-*/

object A3 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        fun fullName() = firstName + " " + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
---

# Methods

## can have named parameters, with defaults
-*/

object A4 {
    val bob = Person("Bob", "TheBuilder")

    //`
    data class Person(val firstName: String, val lastName: String) {
        fun fullName(separator: String = " ") =
            firstName + separator + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob : TheBuilder", bob.fullName(" : "))
            assertEquals("Bob-TheBuilder", bob.fullName(separator = "-"))
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
---

# Static Methods
-*/

object A5 {
    //`
    data class Person(val firstName: String, val lastName: String) {

        companion object {
            fun parse(fullName: String): Person {
                val bits = fullName.split(" ")
                return Person(firstName = bits[0], lastName = bits[1])
            }
        }
    }

    class PersonTests {
        @Test fun `parse`() {
            assertEquals(
                Person("Bob", "TheBuilder"),
                Person.parse("Bob TheBuilder")
            )
        }
    }
//`
}





