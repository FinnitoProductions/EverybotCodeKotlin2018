package frc.robot.auto.mode

import edu.wpi.first.wpilibj.command.Command
import frc.robot.commands.DriveToPosition
import frc.robot.subsystems.Drivetrain
import harkerrobolib.auto.AutoMode

/**
 * Represents the autonomous mode to simply drive forward (baseline) a given distance to score minimum non-zero points.
 *
 * @author Angela Jia
 * @version 10/16/18
 */
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