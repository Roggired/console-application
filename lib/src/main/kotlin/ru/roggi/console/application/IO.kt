package ru.roggi.console.application


fun readFromStdin(invitation: String): String {
    println(invitation)
    return readNotNullString()
}

fun <T> readFromStdin(invitation: String, cast: (string: String) -> T): T {
    println(invitation)
    while (true) {
        try {
            return cast(readNotNullString())
        } catch (e: NumberFormatException) {
            println("Value has wrong type. Please, enter again:")
        }
    }
}

private fun readNotNullString(): String {
    while (true) {
        val userInput = readLine()

        userInput?.let {
            return it.trim()
        }

        println("You have entered nothing. Please, enter again:")
    }
}
