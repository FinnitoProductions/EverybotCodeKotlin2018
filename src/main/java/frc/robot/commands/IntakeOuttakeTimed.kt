package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.TimedCommand
import frc.robot.subsystems.Intake

class IntakeOuttakeTimed(val time: Double, val output:  Double, val direction: Intake.IntakeDirection) : TimedCommand(time) {

    override fun end() {

    }

    override fun execute() {
        Intake.intakeOuttakeCube(output, direction)
    }

}