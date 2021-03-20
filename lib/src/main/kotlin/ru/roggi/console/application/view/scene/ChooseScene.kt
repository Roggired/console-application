package ru.roggi.console.application.view.scene

import ru.roggi.console.application.model.*
import ru.roggi.console.application.view.readNotNullStringFromStdin

typealias Switch<T> = (t: T) -> String

class ChooseIntent(val userInput: String): Intent


/**
 * Use this scene when you need to invite user to choose something and switch scene base on entered value.
 * If user enters "exit", invokes SceneContext.exit
 *
 * Use start(SceneContext, (Intent) -> Unit) if you want to change the calling scene state
 *
 * @param greetings will be printed before reading
 * @param endings will be printed after reading. Use empty string for "println()"
 * @param options should contains possible options for user to enter
 * @param switch should return a route based on userInput. With this route the Router will be called
 * @param T type of State which will be modified
 *
 * @author Roggi
 * @since 1.0
 */
class ChooseScene(
    private val greetings: String,
    private val endings: String,
    private val options: Collection<String>,
    private val switch: Switch<String>,
): Scene {
    override fun start(sceneContext: SceneContext, stateReducer: (Intent) -> Unit) {
        println(greetings)
        var userInput = ""
        while (true) {
            userInput = readNotNullStringFromStdin()

            if (userInput.toLowerCase() == "exit") {
                sceneContext.exit()
                return
            }

            if (options.contains(userInput)) {
                break
            } else {
                println("You have entered wrong option. Possible options: $options")
            }
        }

        switch(userInput)
            .also {
                println(endings)
            }
            .apply {
                stateReducer(ChooseIntent(userInput))

                sceneContext.router.switch(this)
            }
    }

    override fun start(sceneContext: SceneContext) {
        println(greetings)
        var userInput = ""
        while (true) {
            userInput = readNotNullStringFromStdin()

            if (userInput.toLowerCase() == "exit") {
                sceneContext.exit()
                return
            }

            if (options.contains(userInput)) {
                break
            } else {
                println("You have entered wrong option. Possible options: $options")
            }
        }

        switch(userInput)
            .also {
                println(endings)
            }
            .apply {
                sceneContext.router.switch(this)
            }
    }
}