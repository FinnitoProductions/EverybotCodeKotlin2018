package frc.robot.commands

import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Arm
import harkerrobolib.commands.Indefinite
import harkerrobolib.subsystems.HSArm
import harkerrobolib.util.MathUtil

/**
 * Moves the arm indefinitely.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/6/18
 */
class MoveArmManual : Indefinite() {

    init {
        requires(Arm)
    }

    override fun execute() {
        var driverJoystickInput = MathUtil.mapJoystickOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        Arm.moveArmPercentOutput(driverJoystickInput)

        if(Global.HAS_TWO_CONTROLLERS && Math.abs(driverJoystickInput) <= OI.XBOX_DEADBAND) {
            val leftOperatorTrigger = MathUtil.map(OI.operatorGamepad.leftTrigger, 0.0, 1.0,
                    0.0, Arm.MAX_MANUAL_SPEED)
            val rightOperatorTrigger = MathUtil.map(OI.operatorGamepad.rightTrigger, 0.0, 1.0,
                    0.0, Arm.MAX_MANUAL_SPEED)
            if(leftOperatorTrigger > rightOperatorTrigger) {
                Arm.armMotionPercentOutput(leftOperatorTrigger, HSArm.ArmDirection.UP)
            }
            else {
                Arm.armMotionPercentOutput(rightOperatorTrigger, HSArm.ArmDirection.DOWN)
            }
        }
    }
}