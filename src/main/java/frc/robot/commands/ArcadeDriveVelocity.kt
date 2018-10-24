package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import frc.robot.util.MathUtil
import harkerrobolib.commands.IndefiniteCommand

class ArcadeDriveVelocity : IndefiniteCommand() {
    init {
        requires(Drivetrain)
    }

    /**
     * Sets the gamepad speed and turn output
     * to the desired values to move the robot optimally.
     */
    override fun execute() {
        val speed = MathUtil.mapOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND)
        val turn = MathUtil.mapOutput(OI.driverGamepad.leftX, OI.XBOX_DEADBAND)
        Drivetrain.arcadeDrivePercentOutput(speed * OI.JOYSTICK_UP, turn)
    }
}