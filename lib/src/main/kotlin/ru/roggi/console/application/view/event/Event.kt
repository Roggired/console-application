package ru.roggi.console.application.view.event

/**
 * @authorRoggi
 * @since 1.0
 */
interface Event

/**
 * @author Roggi
 * @since 1.0
 */
interface EventListener {
    fun handle(event: Event)
}

/**
 * If you need to do somethings before program's end, create ExitEventListener and add it to SceneContext
 *
 * @author Roggi
 * @since 1.0
 */
interface ExitEventListener {
    fun handle()
}