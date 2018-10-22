package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import frc.robot.util.MathUtil

class TankDrive : Command() {
    init {
        requires(Drivetrain)
    }

    override fun isFinished(): Boolean = false

    override fun execute() {
        val leftOutput = MathUtil.mapOutput(OI.driverGamepad.leftY, OI.XBOX_DEADBAND)
        val rightOutput = MathUtil.mapOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        Drivetrain.tankDrivePercentOutput(leftOutput, rightOutput)

    }

}