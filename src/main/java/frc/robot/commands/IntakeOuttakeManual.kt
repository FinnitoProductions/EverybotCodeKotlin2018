package frc.robot.commands


import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Intake

class IntakeOuttakeManual : Command() {

    init {
        requires(Intake)
    }

    override fun end() {

    }

    override fun execute() {
        var intakeOutput = OI.gamepad.leftTrigger
        var outakeOutput = OI.gamepad.rightTrigger
        if (intakeOutput > outakeOutput) {
            Intake.intakeOuttakeCube(intakeOutput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(outakeOutput, Intake.IntakeDirection.OUT)
        }
    }

    override fun initialize() {

    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}
