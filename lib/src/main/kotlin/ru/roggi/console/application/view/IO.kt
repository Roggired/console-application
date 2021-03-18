package ru.roggi.console.application.view


fun readFromStdin(invitation: String): String {
    println(invitation)
    return readNotNullStringFromStdin()
}

fun <T> readFromStdin(invitation: String, cast: (string: String) -> T): T {
    println(invitation)
    while (true) {
        try {
            return cast(readNotNullStringFromStdin())
        } catch (e: NumberFormatException) {
            println("Value has wrong type. Please, enter again:")
        }
    }
}

fun readNotNullStringFromStdin(): String {
    while (true) {
        val userInput = readLine()

        userInput?.let {
            return it.trim()
        }

        println("You have entered nothing. Please, enter again:")
    }
}
