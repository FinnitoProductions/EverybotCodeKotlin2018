package frc.robot.commands


import edu.wpi.first.wpilibj.command.Command
import frc.robot.Global
import frc.robot.IntakeConstants
import frc.robot.OI
import frc.robot.subsystems.Intake
import frc.robot.util.MathUtil

class IntakeOuttakeManual : Command() {

    init {
        requires(Intake)
    }

    override fun execute() {
        var joystickIntakeInput = MathUtil.mapOutput(OI.driverGamepad.leftTrigger, Global.DEADBAND)
        var joystickOuttakeInput = MathUtil.mapOutput(OI.driverGamepad.rightTrigger, Global.DEADBAND)
        if (joystickIntakeInput > joystickOuttakeInput) {
            Intake.intakeOuttakeCube(joystickIntakeInput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(joystickOuttakeInput, Intake.IntakeDirection.OUT)
        }
    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}
