package ru.roggi.console.application.view.scene

import ru.roggi.console.application.model.Intent

/**
 * Router brings you ability to switch scenes. It invokes Scene.start method and provides SceneContext.
 *
 * @author Roggi
 * @since 1.0
 */
class Router {
    private val scenes: MutableMap<String, Scene> = mutableMapOf()
    val sceneContext = SceneContext(this).apply { addExitListener { exited = true } }
    private var exited = false

    /**
     * Registers the scene on the route for future invoking in switch method.
     * If other scene has been registered on given route, then given scene will overwrite it.
     */
    fun register(route: String, scene: Scene) = scenes.set(route, scene)

    /**
     * Invokes Scene.start of a scene registered on given route.
     */
    fun switch(route: String) {
        if (exited) return

        scenes[route]?.start(sceneContext) ?: throw IllegalArgumentException("No such route")
    }

    fun switch(route: String, stateReducer: (Intent) -> Unit) {
        if (exited) return

        scenes[route]?.start(sceneContext, stateReducer) ?: throw IllegalArgumentException("No such route")
    }
}

