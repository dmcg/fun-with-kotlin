package funWithKotlin.D

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import funWithKotlin.D.D1.Person

val bob = Person("Bob", "TheBuilder")
val objectMapper = ObjectMapper()

/*-
# Extension Functions

^Java codebases tend to end up with value classes and static utility functions that take instances of those classes.
Either a class ends up with the sum of all the things that needs to be done with it - render itself to JSON, and XML,
and protobuf
or we define them as static functions and pass instances to them.

-*/

/*-
# Extension Functions

## 'extend' a type

^ this allows access to the instance of the type being extended, called the receiver
We can only access public methods on the type in extension functions
-*/

object D1 {

    //`
    data class Person(val firstName: String, val lastName: String)

    fun Person.fullName(separator: String = " ") =
        this.firstName + separator + this.lastName

    class ExtensionTests {
        @Test fun fullName() {
            assertEquals("Bob : TheBuilder", bob.fullName(" : "))
            assertEquals("Bob TheBuilder", bob.fullName())
        }
    }
//`
}

/*-
# Extension Functions

## 'this' is implied
-*/

object D1a1 {
    //`
    fun Person.fullName(separator: String = " ") =
        this.firstName + separator + this.lastName
//`
}
object D1a2 {
    //`
    fun Person.fullName(separator: String = " ") =
        firstName + separator + lastName
//`
}


/*-
# Extension Functions

## are statically resolved

^ It is the compile-time type of the variable that decides which one is applied, not the runtime type of the object
-*/

object D3 {

    //`
    open class Person(val firstName: String, val lastName: String)

    class Employee(firstName: String, lastName: String) : Person(firstName, lastName)

    fun Person.fullName(separator: String = " ") = "$firstName$separator$lastName"
    fun Employee.fullName(separator: String = " ") = "$lastName$separator$firstName"

    class ExtensionTests {
        @Test fun fullName() {
            val employeeBob = Employee("Bob", "TheBuilder")
            assertEquals("TheBuilder Bob", employeeBob.fullName())
            assertEquals("Bob TheBuilder", (employeeBob as Person).fullName())
        }
    }
//`
}

/*-
# Extension Functions

## can have other scopes
-*/

object D2 {
    //`
    class ExtensionTests {

        private fun Person.fullName(separator: String = " ") =
            firstName + separator + lastName

        @Test fun fullName() {
            assertEquals("Bob : TheBuilder", bob.fullName(" : "))
        }
    }
//`
}

/*-
# Extension Functions

## are excellent for extending a type in a domain

^ Note that we don't own ObjectNode, so we can't add a method to convert one to a Person even if we wanted to
-*/

object D4 {
    //`
    fun toJsonNode(person: Person): ObjectNode {
        val result = objectMapper.createObjectNode()
        result.put("givenName", person.firstName)
        result.put("surname", person.lastName)
        return result
    }

    fun toPerson(objectNode: ObjectNode) = Person(
        objectNode["givenName"].textValue(),
        objectNode["surname"].textValue()
    )

    class MappingTests {
        @Test fun roundTrip() {
            assertEquals(bob, toPerson(toJsonNode(bob)))
        }
    }
//`
}

/*-
# Extension Functions

## are excellent for extending a type in a domain
-*/

object D5 {
    //`
    fun Person.toJsonNode(): ObjectNode {
        val result = objectMapper.createObjectNode()
        result.put("givenName", firstName)
        result.put("surname", lastName)
        return result
    }

    fun ObjectNode.toPerson() = Person(
        this["givenName"].textValue(),
        this["surname"].textValue()
    )

    class MappingTests {
        @Test fun roundTrip() {
            assertEquals(bob, bob.toJsonNode().toPerson())
        }
    }
//`
}

/*-
# Extension Functions

## are excellent for chaining

^ Reading left to right means that we don't need to run the compiler in our heads to work out what expressions are
evaluated first.
-*/

object D6 {
    fun toJsonNode(person: Person) = D4.toJsonNode(person)

    //`
    fun toJsonString(objectNode: ObjectNode) =
        objectMapper.writeValueAsString(objectNode)

    fun toJsonString(person: Person): String = toJsonString(toJsonNode(person))
//`
}

object D7 {
    fun Person.toJsonNode() = D4.toJsonNode(this)

    //`
    fun ObjectNode.toJsonString() =
        objectMapper.writeValueAsString(this)

    fun Person.toJsonString(): String = this.toJsonNode().toJsonString()

//`
}


/*-
# Extension Properties

## are also a thing
-*/

object D99 {
    //`
    val Person.fullName get() = firstName + " " + lastName

    class ExtensionTests {
        @Test fun fullName() {
            assertEquals("Bob TheBuilder", bob.fullName)
        }
    }
//`
}
