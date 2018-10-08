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
        var driverJoystickIntakeInput = MathUtil.mapOutput(OI.driverGamepad.leftTrigger, OI.XBOX_DEADBAND)
        var driverJoystickOuttakeInput = MathUtil.mapOutput(OI.driverGamepad.rightTrigger, OI.XBOX_DEADBAND)
        if (driverJoystickIntakeInput > driverJoystickOuttakeInput) {
            Intake.intakeOuttakeCube(driverJoystickIntakeInput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(driverJoystickOuttakeInput, Intake.IntakeDirection.OUT)
        }

        var leftOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.leftY, OI.LOGITECH_DEADBAND)
        var rightOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.rightY, OI.LOGITECH_DEADBAND)
        Intake.intakeOuttakeCube(leftOperatorJoystickInput, rightOperatorJoystickInput,
                if (Math.signum(leftOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN,
                if (Math.signum(rightOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN)



    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}
