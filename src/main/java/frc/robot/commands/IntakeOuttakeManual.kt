package frc.robot.commands

import edu.wpi.first.wpilibj.hal.PDPJNI
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Intake
import harkerrobolib.commands.Indefinite
import harkerrobolib.subsystems.HSIntake
import harkerrobolib.util.MathUtil

class IntakeOuttakeManual : Indefinite() {

    init {
        requires(Intake)
    }

    override fun execute() {
        var leftOperatorJoystickInput = 0.0
        var rightOperatorJoystickInput = 0.0

        if (Global.HAS_TWO_CONTROLLERS) {
            leftOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.leftY, OI.LOGITECH_DEADBAND)
            rightOperatorJoystickInput = MathUtil.mapJoystickOutput(OI.operatorGamepad.rightY, OI.LOGITECH_DEADBAND)
            Intake.intakeOuttakeCubeManual(-rightOperatorJoystickInput, leftOperatorJoystickInput)
        }

        if (Math.abs(leftOperatorJoystickInput) <= OI.LOGITECH_DEADBAND && Math.abs(rightOperatorJoystickInput) <= OI.LOGITECH_DEADBAND) {
            val driverIntakeInput = OI.driverGamepad.leftTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.leftTrigger, OI.XBOX_DEADBAND)
            val driverOuttakeInput = OI.driverGamepad.rightTrigger //MathUtil.mapJoystickOutput(OI.driverGamepad.rightTrigger, OI.XBOX_DEADBAND)
            if (driverIntakeInput > driverOuttakeInput) {
                Intake.intakeOuttakeCube(driverIntakeInput, HSIntake.IntakeDirection.IN)
            } else {
                Intake.intakeOuttakeCube(driverOuttakeInput, HSIntake.IntakeDirection.OUT)
            }
        }
    }
}
