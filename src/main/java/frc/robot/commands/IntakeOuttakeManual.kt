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
        var driverJoystickIntakeInput = MathUtil.mapOutput(OI.driverGamepad.leftTrigger, Global.DEADBAND)
        var driverJoystickOuttakeInput = MathUtil.mapOutput(OI.driverGamepad.rightTrigger, Global.DEADBAND)
        if (driverJoystickIntakeInput > driverJoystickOuttakeInput) {
            Intake.intakeOuttakeCube(driverJoystickIntakeInput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(driverJoystickOuttakeInput, Intake.IntakeDirection.OUT)
        }

        var leftOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.leftY, Global.DEADBAND)
        var rightOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.rightY, Global.DEADBAND)
        Intake.intakeOuttakeCube(leftOperatorJoystickInput, rightOperatorJoystickInput, Intake.IntakeDirection.IN, Intake.IntakeDirection.IN)



    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}
