package ru.roggi.console.application.view.scene

import ru.roggi.console.application.model.State
import ru.roggi.console.application.model.StateProducer
import ru.roggi.console.application.model.StateReducer
import ru.roggi.console.application.view.readNotNullStringFromStdin

/**
 * Use this scene when you need to invite user to choose something.
 * If user enters "exit", invokes SceneContext.exit
 *
 * @param greetings will be printed before reading
 * @param endings will be printed after reading. Use empty string for "println()"
 * @param options should contains possible options for user to enter
 * @param switch should return a route based on userInput. With this route the Router will be called
 * @param stateProducer if present, should return a state which will be modified by stateReducer
 * @param stateReducer if present, should apply the user input to state produced by stateProducer
 * @param T type of State which will be modified
 *
 * @author Roggi
 * @since 1.0
 */
class ChooseScene<in T: State>(
    private val greetings: String,
    private val endings: String,
    private val options: Collection<String>,
    private val switch: Switch<String>,
    private val stateProducer: StateProducer<T>?,
    private val stateReducer: StateReducer<T, String>?
): Scene {
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
                stateProducer?.apply {
                    stateReducer?.let { it(this, userInput) }
                }

                sceneContext.router.switch(this)
            }
    }
}

/**
 * Builds ChooseScene with obligatory greetings, endings and switch
 *
 * @param greetings obligatory greetings with which ChooseScene will be created
 * @param endings obligatory endings with which ChooseScene will be created
 * @param options obligatory options with which ChooseScene will be created
 * @param switch obligatory switch with which ChooseScene will be created
 *
 * @author Roggi
 * @since 1.0
 */
fun simpleChooseScene(
    greetings: String,
    endings: String,
    options: Collection<String>,
    switch: Switch<String>
): ChooseScene<State> = ChooseScene(greetings, endings, options, switch, null, null)

/**
 * Builds ChooseScene with obligatory greetings, endings and switch and optional stateReducer and stateProducer
 *
 * @param greetings obligatory greetings with which ChooseScene will be created
 * @param endings obligatory endings with which ChooseScene will be created
 * @param options obligatory options with which ChooseScene will be created
 * @param switch obligatory switch with which ChooseScene will be created
 * @param T type of State for ChooseScene for modification. If created ChooseScene does not need to modify State,
 * specify "State"
 * @param stateProducer optional stateProducer with witch ChooseScene will be created
 * @param stateReducer optional stateReducer with witch ChooseScene will be created
 *
 * @author Roggi
 * @since 1.0
 */
fun <T: State> fullChooseScene(
    greetings: String,
    endings: String,
    options: Collection<String>,
    switch: Switch<String>,
    stateProducer: StateProducer<T>,
    stateReducer: StateReducer<T, String>
): ChooseScene<T> = ChooseScene(greetings, endings, options, switch, stateProducer, stateReducer)