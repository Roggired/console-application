package ru.roggi.console.application

import ru.roggi.console.application.model.Intent
import ru.roggi.console.application.model.State
import ru.roggi.console.application.view.scene.*


class TestState: State {
    var userInput: String = ""
}

class TestScene(private val testState: TestState): StatefulScene<TestState>(testState) {
    override fun start(sceneContext: SceneContext) {
        if (testState.userInput.isEmpty()) {
            println("Hello, World!")
            sceneContext.router.switch("choose1") { intent: Intent ->
                state.userInput = (intent as ChooseIntent).userInput
            }
        } else {
            println("Test scene received: ${testState.userInput}")
            sceneContext.router.switch("choose2")
        }
    }
}

fun main() {
    val testState = TestState()
    val testScene = TestScene(testState)

    Router()
        .apply {
            register("main", testScene)
            register("choose1", ChooseScene(
                "Choose (apple, banana)",
                "",
                listOf("apple", "banana"),
            ) { "main" })
            register("choose2", ChooseScene(
                "Choose (red, yellow)",
                "",
                listOf("red", "yellow")
            ) { "main" })
        }
        .switch("main")
}