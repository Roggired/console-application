package ru.roggi.console.application.scene

import ru.roggi.console.application.readFromStdin

/**
 * Use this scene when you need to invite user to choose something.
 * If user enters "exit", invokes SceneContext.exit
 *
 * @param greetings This string will be printed before reading
 * @param switch Lambda which should return a route based on userInput. With this route the Router will be called
 * @param endings This string will be printed after reading. Use empty string for "println()"
 *
 * @author Roggi
 * @since 1.0
 */
class ChooseScene(
    private val greetings: String,
    private val switch: (userInput: String) -> String,
    private val endings: String
): Scene {
    override fun start(sceneContext: SceneContext) {
        val userInput = readFromStdin(greetings)

        if (userInput.toLowerCase() == "exit") {
            sceneContext.exit()
            return
        }

        switch(userInput)
            .also {
                println(endings)
            }
            .apply { sceneContext.router.switch(this) }
    }
}