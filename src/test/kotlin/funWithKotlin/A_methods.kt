package funWithKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/*-
# Methods
-*/

/*-
# Methods

## are public and final by default
-*/
object A1 {
    //`
    open class Person(val firstName: String, val lastName: String) {
        open fun fullName(): String {
            return firstName + " " + lastName
        }
    }

    class Employee(firstName: String, lastName: String) : Person(firstName, lastName) {
        override fun fullName(): String {
            return lastName + " " + firstName
        }
    }
//`
}

/*-
# Methods

## can be a single expression

^ Aiming to make every method a single expression is a good way to improve your Kotlin
-*/

object A2a {
    //`
    class Person(val firstName: String, val lastName: String) {
        fun fullName(): String {
            return firstName + " " + lastName
        }
    }
//`
}
object A2b {
    //`
    class Person(val firstName: String, val lastName: String) {
        fun fullName(): String = firstName + " " + lastName
    }
//`
}

/*-
---

# Methods

## can have an implicit return type
-*/
object A3a {
    //`
    class Person(val firstName: String, val lastName: String) {
        fun fullName(): String = firstName + " " + lastName
    }
//`
}
object A3b {
    //`
    class Person(val firstName: String, val lastName: String) {
        fun fullName() = firstName + " " + lastName
    }
//`
}

/*-
---

# Methods

## can be called with named arguments, or default parameters

^ Then we can reorder arguments.
-*/

object A4 {
    val bob = Person("Bob", "TheBuilder")

    //`
    class Person(val firstName: String, val lastName: String) {
        fun fullName(separator: String = " ") =
            firstName + separator + lastName
    }

    class PersonTests {
        @Test fun names() {
            assertEquals("Bob : TheBuilder", bob.fullName(" : "))
            assertEquals("Bob - TheBuilder", bob.fullName(separator = " - "))
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
---

# Static Methods

## are declared on a companion object

^Companion objects can implement interfaces, and be passed around, making them more
use than Java statics
You do have to add @JvmStatic annotations to interop with Java
-*/

object A5 {
    //`
    data class Person(val firstName: String, val lastName: String) {

        companion object {
            @JvmStatic fun parse(fullName: String): Person {
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





