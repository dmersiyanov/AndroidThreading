package com.dmity.androidthreading

open class Leg: Any() {

    var isRunning = true

    fun stop() {
        isRunning = false
        steps = 0
    }

    companion object {
        var steps: Int = 0
    }

}