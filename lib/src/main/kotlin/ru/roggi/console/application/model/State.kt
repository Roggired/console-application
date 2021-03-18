package ru.roggi.console.application.model

interface State

typealias StateProducer<T> = () -> T

typealias StateReducer<T, R> = (StateProducer<T>, R) -> Unit

typealias StateTwoReducer<T, R, V> = (StateProducer<T>, R, V) -> Unit