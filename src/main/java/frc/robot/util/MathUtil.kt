package frc.robot.util

import frc.robot.Global

object MathUtil {
    fun constrainOutput(output: Double, maxSpeed: Double, minSpeed: Double) : Double {
        if(output > maxSpeed) {
            return maxSpeed
        }
        if(output < minSpeed) {
            return minSpeed
        }
        return output
    }

    /**
     * Constrains gamepad  output from [-deadband, deadband] to 0.
     * Maps output between [deadband, 1] and [-1, -deadband]
     * to  [0, 1] and [-1, 0] respectively.
     */
    fun mapOutput(givenOutput: Double, deadband: Double): Double {
        if (Math.abs(givenOutput) <= deadband) return 0.0
        if (givenOutput > 0) return (givenOutput - deadband) / (1 - deadband)
        else return (givenOutput + deadband) / (1 - deadband)
    }
}