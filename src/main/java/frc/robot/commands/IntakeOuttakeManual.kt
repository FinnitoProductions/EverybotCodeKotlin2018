package frc.robot.commands


import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.OI
import frc.robot.subsystems.Intake
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

class IntakeOuttakeManual : IndefiniteCommand() {

    init {
        requires(Intake)
    }

    override fun execute() {
        val driverJoystickIntakeInput = OI.driverGamepad.leftTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.leftTrigger, OI.XBOX_DEADBAND)
        val driverJoystickOuttakeInput = OI.driverGamepad.rightTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.rightTrigger, OI.XBOX_DEADBAND)
        if (driverJoystickIntakeInput > driverJoystickOuttakeInput) {
            Intake.intakeOuttakeCube(driverJoystickIntakeInput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(driverJoystickOuttakeInput, Intake.IntakeDirection.OUT)
        }

        SmartDashboard.putNumber("Left Trigger", OI.driverGamepad.leftTrigger);
        SmartDashboard.putNumber("Right Trigger", OI.driverGamepad.rightTrigger);

        /* 
        val leftOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.leftY, OI.LOGITECH_DEADBAND)
        val rightOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.rightY, OI.LOGITECH_DEADBAND)
        Intake.intakeOuttakeCube(leftOperatorJoystickInput, rightOperatorJoystickInput,
                if (Math.signum(leftOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN,
                if (Math.signum(rightOperatorJoystickInput).toInt() == OI.JOYSTICK_UP) Intake.IntakeDirection.OUT else Intake.IntakeDirection.IN)
        */

    }
}
