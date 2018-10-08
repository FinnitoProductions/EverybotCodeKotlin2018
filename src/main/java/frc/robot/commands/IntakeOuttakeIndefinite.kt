package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Intake

class IntakeOuttakeIndefinite(val speed : Double) : Command() {

    init {
        requires(Intake)
    }

    override fun isFinished() = false

    override fun execute() {
        Intake.intakeOuttakeCube(speed, if (Math.signum(speed).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN)
    }
}