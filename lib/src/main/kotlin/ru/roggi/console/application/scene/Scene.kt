
package ru.roggi.console.application.scene

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
