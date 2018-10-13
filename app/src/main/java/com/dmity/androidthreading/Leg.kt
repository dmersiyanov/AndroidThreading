package com.dmity.androidthreading

open class Leg: Any() {

    var isRunning = true

    fun stop() {
        isRunning = false
    }

}