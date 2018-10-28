package frc.robot.commands

import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

/**
 * Drives the robot using the tank drive strategy - using one joystick to control movement on
 * each side of the robot.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/21/18
 */
class TankDriveVelocity : IndefiniteCommand() {
    init {
        requires(Drivetrain)
    }

    override fun execute() {
        val leftOutput = MathUtil.mapJoystickOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND)
        val rightOutput = MathUtil.mapJoystickOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        Drivetrain.tankDrivePercentOutput(leftOutput, rightOutput)
        println("TANK DRIVING")
    }

}