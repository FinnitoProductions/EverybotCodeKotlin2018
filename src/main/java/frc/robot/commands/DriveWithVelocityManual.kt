package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.DrivetrainConstants
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

class DriveWithVelocityManual : Command() {
    var speed = 0.0
    var turn = 0.0

    override fun end() {

    }

    /**
     * Sets the gamepad speed and turn output
     * to the desired values to move the robot optimally.
     */
    override fun execute() {
        speed = mapOutput(OI.gamepad.leftY)
        turn = mapOutput(OI.gamepad.leftX)
        Drivetrain.arcadeDrivePercentOutput(speed, turn)
    }

    /**
     * Constrains gamepad  output from [-deadband, deadband] to 0.
     * Maps output between [deadband, 1] and [-1, -deadband]
     * to  [0, 1] and [-1, 0] respectively.
     */
    private fun mapOutput(givenOutput: Double): Double {
        if (Math.abs(givenOutput) <= Global.DEADBAND) return 0.0
        if (givenOutput > 0) return (givenOutput - Global.DEADBAND) / (1 - Global.DEADBAND)
        else return (givenOutput + Global.DEADBAND) / (1 - Global.DEADBAND)
    }


    override fun initialize() {

    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}