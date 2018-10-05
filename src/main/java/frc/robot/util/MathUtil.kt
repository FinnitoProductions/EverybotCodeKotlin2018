package frc.robot.util

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

}