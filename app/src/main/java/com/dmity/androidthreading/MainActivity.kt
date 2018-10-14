package com.dmity.androidthreading

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var leftLeg: LeftLeg
    private lateinit var rightLeg: RightLeg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftLeg = LeftLeg()
        rightLeg = RightLeg()

    }


    override fun onStop() {
        super.onStop()
        leftLeg.stop()
        rightLeg.stop()
    }

    public override fun onStart() {
        super.onStart()
        Thread(leftLeg).start()
        Thread(rightLeg).start()
    }

    @Synchronized
    private fun makeStep(text: String) {
        println(text)
        runOnUiThread { tvText.text = text }
    }

    private inner class LeftLeg : Runnable, Leg() {
        override fun run() = synchronized(tvText) {

            while (isRunning) {

                try {
                    Thread.sleep(1000)
                    steps += 1
                    makeStep("Left step #$steps")

                    (tvText as java.lang.Object).notify()
                    (tvText as java.lang.Object).wait()


                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
    }


    private inner class RightLeg : Runnable, Leg() {
        override fun run() = synchronized(tvText) {

            while (isRunning) {

                try {

                    Thread.sleep(1000)
                    steps += 1
                    makeStep("Right step #$steps")

                    (tvText as java.lang.Object).notify()
                    (tvText as java.lang.Object).wait()


                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}




