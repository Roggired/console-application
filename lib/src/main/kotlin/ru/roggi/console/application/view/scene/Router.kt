package ru.roggi.console.application.view.scene

/**
 * Router brings you ability to switch scenes. It invokes Scene.start method and provides SceneContext.
 *
 * @author Roggi
 * @since 1.0
 */
class Router {
    private val scenes: MutableMap<String, Scene> = mutableMapOf()
    val sceneContext = SceneContext(this)


    /**
     * Registers the scene on the route for future invoking in switch method.
     * If other scene has been registered on given route, then given scene will overwrite it.
     */
    fun register(route: String, scene: Scene) = scenes.set(route, scene)

    /**
     * Invokes Scene.start of a scene registered on given route.
     */
    fun switch(route: String) = scenes[route]?.start(sceneContext) ?: throw IllegalArgumentException("No such route")
}

