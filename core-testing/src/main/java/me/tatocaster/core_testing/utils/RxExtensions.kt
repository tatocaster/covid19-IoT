package me.tatocaster.core_testing.utils

import io.reactivex.observers.TestObserver

fun <T> TestObserver<T>.assertCompleteAndDispose() {
    return assertComplete()
        .assertNoErrors()
        .dispose()
}


fun <T> TestObserver<T>.assertNotCompleteAndDispose() {
    return assertNotComplete()
        .dispose()
}

fun <T> TestObserver<T>.assertErrorMessageAndDispose(message: String) {
    return assertErrorMessage(message)
        .dispose()
}