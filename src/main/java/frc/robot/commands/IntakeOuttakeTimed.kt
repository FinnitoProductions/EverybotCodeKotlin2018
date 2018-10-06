package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.TimedCommand
import frc.robot.Global
import frc.robot.subsystems.Intake
import frc.robot.util.MathUtil

class IntakeOuttakeTimed(val time: Double, val output: Double, val direction: Intake.IntakeDirection) : TimedCommand(time) {

    init {
        requires(Intake)
    }

    override fun execute() {
        var mappedOutput = MathUtil.mapOutput(output, Global.DEADBAND)
        Intake.intakeOuttakeCube(mappedOutput, direction)
    }

}