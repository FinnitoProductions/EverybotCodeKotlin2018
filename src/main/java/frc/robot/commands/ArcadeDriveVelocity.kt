package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

class ArcadeDriveVelocity : IndefiniteCommand() {
    init {
        requires(Drivetrain)
    }

    /**
     * Sets the gamepad speed and turn output
     * to the desired values to move the robot optimally.
     */
    override fun execute() {
        val speed = MathUtil.mapJoystickOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND)
        val turn = MathUtil.mapJoystickOutput(OI.driverGamepad.leftX, OI.XBOX_DEADBAND)
        Drivetrain.arcadeDrivePercentOutput(speed * OI.JOYSTICK_UP, turn)
    }
}