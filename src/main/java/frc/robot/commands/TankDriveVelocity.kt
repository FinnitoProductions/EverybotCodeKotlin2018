package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

class TankDriveVelocity : IndefiniteCommand() {
    init {
        requires(Drivetrain)
    }

    override fun execute() {
        val leftOutput = MathUtil.mapJoystickOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND)
        val rightOutput = MathUtil.mapJoystickOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        Drivetrain.tankDrivePercentOutput(leftOutput, rightOutput)

    }

}