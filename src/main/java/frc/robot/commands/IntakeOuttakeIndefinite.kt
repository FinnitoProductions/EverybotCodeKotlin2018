package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.subsystems.Intake
import harkerrobolib.commands.IndefiniteCommand

class IntakeOuttakeIndefinite(val speed: Double, val direction: Intake.IntakeDirection) : IndefiniteCommand() {

    init {
        requires(Intake)
    }
    override fun execute() {
        Intake.intakeOuttakeCube(speed, direction)
    }

}