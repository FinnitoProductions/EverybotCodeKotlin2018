package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Intake
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

class IntakeOuttakeManual : IndefiniteCommand() {

    init {
        requires(Intake)
    }

    override fun execute() {
        var leftOperatorJoystickInput = 0.0
        var rightOperatorJoystickInput = 0.0

        if (Global.HAS_TWO_CONTROLLERS) {
            leftOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.leftY, OI.LOGITECH_DEADBAND)
            rightOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.rightY, OI.LOGITECH_DEADBAND)
            Intake.intakeOuttakeCube(leftOperatorJoystickInput, rightOperatorJoystickInput,
                    if (Math.signum(leftOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN,
                    if (Math.signum(rightOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN)
        }

        if (Math.abs(leftOperatorJoystickInput) <= OI.LOGITECH_DEADBAND && Math.abs(rightOperatorJoystickInput) <= OI.LOGITECH_DEADBAND) {
            val driverIntakeInput = OI.driverGamepad.leftTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.leftTrigger, OI.XBOX_DEADBAND)
            val driverOuttakeInput = OI.driverGamepad.rightTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.rightTrigger, OI.XBOX_DEADBAND)
            if (driverIntakeInput > driverOuttakeInput) {
                Intake.intakeOuttakeCube(driverIntakeInput, Intake.IntakeDirection.IN)
            } else {
                Intake.intakeOuttakeCube(driverOuttakeInput, Intake.IntakeDirection.OUT)
            }
        }
    }
}
