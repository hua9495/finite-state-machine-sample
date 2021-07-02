package com.alexchan.finite_state_machine.util

import com.alexchan.finite_state_machine.service.ServiceContainer

/**
 *  Executes the given block if the receiver is null or returns the receiver as a non-null type otherwise. The guard
 *  block must return from the enclosing function.
 */
inline fun <T, R> T?.guard(block: () -> R): T {
    if (this == null) {
        block()
        throw IllegalArgumentException("guard block must return from enclosing function")
    }
    return this
}

val services: ServiceContainer
    get() {
        return ServiceContainer
    }