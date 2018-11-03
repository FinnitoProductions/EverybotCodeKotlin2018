package frc.robot.commands

import edu.wpi.first.wpilibj.command.TimedCommand
import frc.robot.subsystems.Drivetrain

/**
 *
 * @param   turn    positive angle at which robot should turn
 * @param   direction
 * @version 11/3/18
 */
class TurnToAngleTimed(time : Double, val turn : Double, val direction : Drivetrain.TurnDirection) : TimedCommand(time) {

    override fun execute() {
     Drivetrain.arcadeDrivePercentOutput(0.0, (if(direction == Drivetrain.TurnDirection.LEFT) -1 else 1)*turn)
    }
}