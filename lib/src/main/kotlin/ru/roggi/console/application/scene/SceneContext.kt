package ru.roggi.console.application.scene

import ru.roggi.console.application.event.ExitEventListener

/**
 * SceneContext provides to scenes ability to use router and also to share any data between each other.
 * Besides, SceneContext provides exit method to notifies listeners about program's end.
 *
 * @author Roggi
 * @since 1.0
 */
class SceneContext(val router: Router) {
    private val data: MutableMap<String, Any> = mutableMapOf()
    private val exitListeners: MutableList<ExitEventListener> = mutableListOf()


    /**
     * Saves the value with given key.
     */
    fun put(key: String, value: Any) = data.set(key, value)

    /**
     * Return the value by given key.
     */
    fun get(key: String) = data[key]

    /**
     * Notifies exit event listeners.
     */
    fun exit() = exitListeners.forEach { it.handle() }

    fun addExitListener(exitEventListener: ExitEventListener) = exitListeners.add(exitEventListener)
}