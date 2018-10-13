package com.dmity.androidthreading

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

//    private var isRunning = true
    private lateinit var leftLeg: LeftLeg
    private lateinit var rightLeg: RightLeg
    private var steps: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftLeg = LeftLeg()
        rightLeg = RightLeg()

    }

    override fun onStop() {
        super.onStop()
//        isRunning = false
        leftLeg.stop()
        rightLeg.stop()
    }

    public override fun onStart() {
        super.onStart()
        Thread(leftLeg).start()
        Thread(rightLeg).start()
    }

    private inner class LeftLeg : Runnable, Leg() {
        override fun run() {
            synchronized(steps) {

                while (isRunning) {
                    Log.e("Threads", "Left step #$steps")
                    steps += 1

                }

            }
        }
    }

    private inner class RightLeg : Runnable, Leg() {
        override fun run() {
            synchronized(steps) {

                while (isRunning) {
                    Log.e("Threads", "Right step #$steps")
                    steps += 1
                }

            }
        }
    }



}
