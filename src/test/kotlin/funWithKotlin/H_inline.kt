package funWithKotlin

import com.fasterxml.jackson.databind.node.ObjectNode
import funWithKotlin.D.D1
import funWithKotlin.D.objectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

private fun doSomethingWith(person: Person) = person

/*-
# Inline Functions
-*/

/*-
# Inline Functions

## can be used for control flow
-*/

object H1 {
    //`
    fun Person.ifBlankFirstName(f: () -> String): Person =
        if (firstName.isNotBlank())
            this
        else
            this.copy(firstName = f())

    class IfBlankFirstNameTests {
        @Test fun `returns a copy if first name is blank`() {
            assertEquals(
                Person("UNKNOWN", "Person"),
                Person("", "Person").ifBlankFirstName { "UNKNOWN" }
            )
        }
        @Test fun `returns receiver if first name not blank`() {
            assertEquals(
                bob,
                bob.ifBlankFirstName { "UNKNOWN" }
            )
        }
    }
//`
}

/*-
# Inline Functions

## can be used for control flow
-*/

object H2 {
    //`
    inline fun Person.ifBlankFirstName(f: () -> String): Person =
        if (firstName.isNotBlank())
            this
        else
            this.copy(firstName = f())

    class IfBlankFirstNameTests {
        @Test fun `early return`() {
            Person("", "Jamaflip").ifBlankFirstName {
                return
            }
            fail("did not return")
        }
    }
//`
}

/*-
# Inline Functions

## lots of nice built-ins
-*/

object H3 {
    //`
    /**
     * Calls the specified function [block] with `this` value as its argument and returns its result.
     */
    inline fun <T, R> T.let(block: (T) -> R): R {
        return block(this)
    }

    fun parse(fullName: String): Person {
        val bits = fullName.split(" ")
        return Person(firstName = bits[0], lastName = bits[1])
    }

    fun letParse(fullName: String) = fullName.split(" ").let { bits ->
        Person(firstName = bits[0], lastName = bits[1])
    }
//`
}

/*-
# Inline Functions

## lots of nice built-ins
-*/

object H4 {
    //`
    fun printlnDebuggingBefore(person: Person) =
        doSomethingWith(person)

    fun printlnDebuggingAfter(person: Person): Person {
        val result = doSomethingWith(person)
        println(result)
        return result
    }
//`
}

/*-
# Inline Functions

## lots of nice built-ins
-*/

object H5 {
    //`
    /**
     * Calls the specified function [block] with `this` value
     * as its argument and returns `this` value.
     */
    inline fun <T> T.also(block: (T) -> Unit): T {
        block(this)
        return this
    }

    fun printlnDebuggingBefore(person: Person) =
        doSomethingWith(person)

    fun printlnDebuggingAfter(person: Person) =
        doSomethingWith(person).also { println(it) }
//`
}

/*-
# Inline Functions

## lots of nice built-ins
-*/

object H6 {
    //`
    fun Person.toJsonNode(): ObjectNode {
        val result = objectMapper.createObjectNode()
        result.put("givenName", firstName)
        result.put("surname", lastName)
        return result
    }
//`
}

/*-
# Inline Functions

## lots of nice built-ins
-*/

object H7 {
    //`
    /**
     * Calls the specified function [block] with `this` value as its receiver and returns `this` value.
     */
    inline fun <T> T.apply(block: T.() -> Unit): T {
        block()
        return this
    }

    fun Person.toJsonNode() = objectMapper.createObjectNode().apply {
        this.put("givenName", firstName)
        put("surname", lastName)
    }
//`
}