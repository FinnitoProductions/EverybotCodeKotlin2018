package frc.robot.commands

import edu.wpi.first.wpilibj.command.TimedCommand
import frc.robot.subsystems.Intake

/**
 * Intakes or outtakes cubes for a certain amount of time using a specified output and direction.
 *
 * @param   time    extent of time the command runs for
 * @param   output  rate at which intake wheels will turn
 * @param   direction   specifies whether wheels will intake or outtake
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/3/18
 */
class IntakeOuttakeTimed(val time: Double, val output: Double, val direction: Intake.IntakeDirection) : TimedCommand(time) {

    init {
        requires(Intake)
    }

    override fun execute() {
        Intake.intakeOuttakeCube(output, direction)
    }

}