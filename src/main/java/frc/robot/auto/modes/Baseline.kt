package frc.robot.auto.modes

import edu.wpi.first.wpilibj.command.Command
import frc.robot.commands.DriveToPosition
import frc.robot.subsystems.Drivetrain
import harkerrobolib.auto.AutoMode

/**
 * Represents the autonomous mode to simply drive forward (baseline) a given distance to score minimum non-zero points.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/16/18
 */
class Baseline(val startLoc : AutoMode.StartLocation) : AutoMode(startLoc, leftCommands = DriveToPosition(DISTANCE),
        rightCommands = DriveToPosition(DISTANCE)) {

    init {
        requires(Drivetrain)
    }

    companion object {
        const val DISTANCE = 15.0
    }

}