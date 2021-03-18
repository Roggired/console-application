
package ru.roggi.console.application.view.scene

import ru.roggi.console.application.model.State

/**
 * Scene is a base interface for ConsoleApplication. It determines a state of your view.
 * Think about scenes in context of state machine. Split your view on scenes and specify
 * switching between them by using router that can be accessed via scene context.
 *
 * @author Roggi
 * @since 1.0
 */
interface Scene {
    fun start(sceneContext: SceneContext)
}

abstract class StatefulScene(protected val state: State): Scene

typealias Switch<T> = (t: T) -> String

typealias SwitchTwo<T, E> = (t: T, e: E) -> String
