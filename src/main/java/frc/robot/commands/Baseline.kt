package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.subsystems.Drivetrain
import harkerrobolib.auto.AutoMode

class Baseline(val startLoc : AutoMode.StartLocation) : AutoMode(startLoc) {

    init {
        requires(Drivetrain)
    }

    override fun getRightCommands(): Command {
        return DriveToPosition(DISTANCE)
    }

    override fun getCenterCommands(): Command {
        return centerAutonNotDefined
    }

    override fun getLeftCommands(): Command {
        return DriveToPosition(DISTANCE)
    }

    companion object {
        const val DISTANCE = 15.0
    }

}