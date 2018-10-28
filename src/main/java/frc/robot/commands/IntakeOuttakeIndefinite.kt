package frc.robot.commands

import frc.robot.subsystems.Intake
import harkerrobolib.commands.IndefiniteCommand

/**
 * Intakes or outtakes cubes indefinitely based on a specified speed and direction.
 *
 * @param   speed   rate at which intake wheels will spin
 * @param   direction   specifies whether wheels will intake or outtake
 *
 * @author  Angela Jia
 * @author  Finn Franks
 * @version 10/7/18
 */
class IntakeOuttakeIndefinite(val speed: Double, val direction: Intake.IntakeDirection) : IndefiniteCommand() {

    init {
        requires(Intake)
    }
    override fun execute() {
        Intake.intakeOuttakeCube(speed, direction)
    }

}