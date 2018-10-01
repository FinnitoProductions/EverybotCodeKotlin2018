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
        speed = mapSpeed(OI.gamepad.leftY)
        turn = mapTurn(OI.gamepad.leftX)
        Drivetrain.arcadeDrivePercentOutput(speed, turn)
    }

    /**
     * Constrains gamepad speed output from [-deadband, deadband] to 0.
     * Maps output between [deadband, 1] and [-1, -deadband]
     * to  [0, 1] and [-1, 0] respectively.
     */
    private fun mapSpeed(givenSpeed: Double): Double {
        if (Math.abs(givenSpeed) <= Global.DEADBAND) return 0.0
        if (givenSpeed > 0) return (givenSpeed - Global.DEADBAND) / (1 - Global.DEADBAND)
        else return (givenSpeed + Global.DEADBAND) / (1 - Global.DEADBAND)
    }

    /**
     * Constrains gamepad turn output from [-deadband, deadband] to 0.
     * Maps output between [deadband, 1] and [-1, -deadband]
     * to  [0, 1] and [-1, 0] respectively.
     */
    private fun mapTurn(givenTurn: Double): Double {
        if (Math.abs(givenTurn) <= Global.DEADBAND) return 0.0
        if (givenTurn > 0) return (givenTurn - Global.DEADBAND) / (1 - Global.DEADBAND)
        else return (givenTurn + Global.DEADBAND) / (1 - Global.DEADBAND)

    }

    override fun initialize() {

    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}