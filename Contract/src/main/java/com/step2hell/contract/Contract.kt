package com.step2hell.contract

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.contracts.InvocationKind


@ExperimentalContracts
inline fun calledOneTimeOnly(run: () -> Unit) {
    // do something
    contract {
        callsInPlace(run, InvocationKind.EXACTLY_ONCE)
    }
    run()
}

@ExperimentalContracts
fun initValue() {
    val intValue: Int
    calledOneTimeOnly {
        // Does not compile if comment contract block in calledOneTimeOnly:
        // Captured values initialization is forbidden due to possible reassignment.
        intValue = 1
        println("intValue=$intValue")
    }
}

@ExperimentalContracts
fun main() {
    initValue()
}
