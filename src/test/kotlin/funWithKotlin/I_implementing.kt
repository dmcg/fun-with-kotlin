package funWithKotlin

import java.io.Writer

/*-
# Implementing Function Types
-*/

/*-
# Implementing Function Types

## with lambdas
-*/

object I1 {
    //`
    fun lambdas(people: Iterable<Person>) {
        val strings0: List<String> = people.map { person: Person -> person.toString() }
        val strings1: List<String> = people.map { person -> person.toString() }
        val strings2: List<String> = people.map { it.toString() }
    }
//`
}

/*-
# Implementing Function Types

## with values
-*/

object I1a1 {
    //`
    fun lambdas(people: Iterable<Person>) {
        val lambdaValue: (Person) -> String = { it.lastName + " " + it.firstName }
        val strings0: List<String> = people.map(lambdaValue)

        val funValue = fun(person: Person) = person.firstName + " " + person.lastName
        val strings1: List<String> = people.map(funValue)
    }
//`
}

/*-
# Implementing Function Types

## with function reference
-*/

object I1a {
    //`
    fun converterFunction(person: Person) = person.firstName + " " + person.lastName

    fun functionReferences(people: Iterable<Person>) {
        val strings0: List<String> = people.map(::converterFunction)
        val strings1: List<String> = people.map(Person::toString)
    }
//`
}

/*-
# Implementing Function Types

## with a class
-*/
object I2 {
    //`
    class Converter(val separator: String): (Person) -> String {
        override fun invoke(person: Person): String = person.firstName + separator + person.lastName
    }

    fun classImplementingFunction(people: Iterable<Person>) {
        val converter = Converter(" : ")
        val strings0: List<String> = people.map(converter)
    }
//`
}

/*-
# Implementing Function Types

## with objects
-*/
object I2a {
    //`
    fun objects(people: Iterable<Person>) {
        val anonymousConverterObject = object : (Person) -> String {
            override fun invoke(person: Person) = person.firstName + " " + person.lastName
        }
        val strings0: List<String> = people.map(anonymousConverterObject)

        val strings1: List<String> = people.map(TopLevelConverterObject)
    }

    object TopLevelConverterObject : (Person) -> String {
        override fun invoke(person: Person) = person.firstName + " " + person.lastName
    }
//`
}

/*-
# Implementing Function Types

## with a method
-*/
object I3 {
    //`
    fun method(people: Iterable<Person>) {
        val converter = Converter(" : ")
        val strings0: List<String> = people.map(converter::convert)
    }

    class Converter(val separator: String) {
        fun convert(person: Person) = person.firstName + separator + person.lastName
    }
//`
}

