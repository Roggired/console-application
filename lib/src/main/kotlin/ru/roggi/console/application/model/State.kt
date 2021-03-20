package ru.roggi.console.application.model

interface State

typealias StateProducer<T> = () -> T

interface Intent