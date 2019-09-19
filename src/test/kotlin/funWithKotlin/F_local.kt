package funWithKotlin


/*-
# Local Functions
-*/

/*-
# Local Functions

## avoid scope pollution
-*/

object F1 {

    //`
    fun complicatedThing(aString: String, anInt: Int): String {
        fun nitpickyDetail(aString: String, anInt: Int): String =
            aString + anInt

        //...

        return nitpickyDetail(aString, anInt)
    }
//`
}

/*-
# Local Functions

## close over parent scope
-*/

object F2 {

    //`
    fun complicatedThing(aString: String, anInt: Int): String {
        fun nitpickyDetail(): String =
            aString + anInt

        //...

        return nitpickyDetail()
    }
//`
}
