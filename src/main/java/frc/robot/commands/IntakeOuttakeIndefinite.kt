package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.subsystems.Intake

class IntakeOuttakeIndefinite(val speed: Double, val direction: Intake.IntakeDirection) : Command() {

    init {
        requires(Intake)
    }

    override fun isFinished() = false

    override fun execute() {
        Intake.intakeOuttakeCube(speed, direction)
    }

}