package funWithKotlin

import java.io.Writer

/*-
# Implementing Function Types
-*/

/*-
# Implementing Function Types
-*/

object I1 {
    //`
    fun Person.writeOn(writer: Writer, renderer: (Person) -> String) =
        writer.write(renderer(this))

    fun variations(writer: Writer, person: Person) {
        person.writeOn(writer) { it.toString() }
        person.writeOn(writer, Person::toString)

        fun converterFunction(person: Person) = person.firstName + " " + person.lastName
        person.writeOn(writer, ::converterFunction)

        val converterValue: (Person) -> String = { it.lastName + " " + it.firstName }
        person.writeOn(writer, converterValue)

        val converterValue2 = fun(person: Person) = person.firstName + " " + person.lastName
        person.writeOn(writer, converterValue2)
    }
//`
}

/*-
# Implementing Function Types
-*/
object I2 {
    //`
    fun Person.writeOn(writer: Writer, renderer: (Person) -> String) =
        writer.write(renderer(this))

    fun moreVariations(writer: Writer, person: Person) {
        person.writeOn(writer, Converter(" : "))

        val anonymousConverterObject = object : (Person) -> String {
            override fun invoke(person: Person) = person.firstName + " " + person.lastName
        }
        person.writeOn(writer, anonymousConverterObject)

        person.writeOn(writer, TopLevelConverterObject)
    }

    class Converter(val separator: String): (Person) -> String {
        override fun invoke(person: Person): String = person.firstName + separator + person.lastName
    }

    object TopLevelConverterObject : (Person) -> String {
        override fun invoke(person: Person) = person.firstName + " " + person.lastName
    }
//`
}

/*-
# Implementing Function Types
-*/
object I3 {
    //`
    fun Person.writeOn(writer: Writer, renderer: (Person) -> String) =
        writer.write(renderer(this))

    fun justOneMoreVariation(writer: Writer, person: Person) {
        val converter2 = Converter(" : ")
        person.writeOn(writer, converter2::convert)
    }

    class Converter(val separator: String) {
        fun convert(person: Person) = person.firstName + separator + person.lastName
    }
//`
}

