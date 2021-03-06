package frc.robot.commands

import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.Indefinite
import harkerrobolib.util.MathUtil

/**
 * Drives the robot using the arcade drive strategy - using one joystick to control movement on
 * both sides of the robot.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 9/27/18
 */
class ArcadeDriveVelocity : Indefinite() {
    init {
        requires(Drivetrain)
    }

    /**
     * Sets the gamepad speed and turn output
     * to the desired values to move the robot optimally.
     */
    override fun execute() {
        val speed = -MathUtil.mapJoystickOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND) * 0.75
        val turn = MathUtil.mapJoystickOutput(OI.driverGamepad.leftX, OI.XBOX_DEADBAND) * 0.75
        Drivetrain.arcadeDrivePercentOutput(speed * OI.JOYSTICK_UP, turn)
    }
}