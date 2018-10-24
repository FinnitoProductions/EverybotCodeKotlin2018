package frc.robot.commands


import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Intake
import frc.robot.util.MathUtil
import harkerrobolib.commands.IndefiniteCommand

class IntakeOuttakeManual : IndefiniteCommand() {

    init {
        requires(Intake)
    }

    override fun execute() {
        val driverJoystickIntakeInput = MathUtil.mapOutput(OI.driverGamepad.leftTrigger, OI.XBOX_DEADBAND)
        val driverJoystickOuttakeInput = MathUtil.mapOutput(OI.driverGamepad.rightTrigger, OI.XBOX_DEADBAND)
        if (driverJoystickIntakeInput > driverJoystickOuttakeInput) {
            Intake.intakeOuttakeCube(driverJoystickIntakeInput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(driverJoystickOuttakeInput, Intake.IntakeDirection.OUT)
        }

        val leftOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.leftY, OI.LOGITECH_DEADBAND)
        val rightOperatorJoystickInput = MathUtil.mapOutput(OI.operatorGamepad.rightY, OI.LOGITECH_DEADBAND)
        Intake.intakeOuttakeCube(leftOperatorJoystickInput, rightOperatorJoystickInput,
                if (Math.signum(leftOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN,
                if (Math.signum(rightOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN)


    }
}
