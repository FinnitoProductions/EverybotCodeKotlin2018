package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Arm
import harkerrobolib.commands.IndefiniteCommand
import harkerrobolib.util.MathUtil

class MoveArmManual : IndefiniteCommand() {

    init {
        requires(Arm)
    }

    override fun execute() {

        var driverJoystickInput = OI.driverGamepad.rightY //MathUtil.mapJoystickOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        // if (Arm.getTalonCurrent() >= Arm.TALON_CURRENT_SPIKE)
        //     joystickInput = 0.0
        if (Math.signum(driverJoystickInput).toInt() == OI.JOYSTICK_UP)
            Arm.armMotionPercentOutput(driverJoystickInput, Arm.ArmDirection.UP)
        else
            Arm.armMotionPercentOutput(driverJoystickInput, Arm.ArmDirection.DOWN)
        SmartDashboard.putNumber("Right Y", driverJoystickInput)

        if(Global.HAS_TWO_CONTROLLERS && Math.abs(driverJoystickInput) <= OI.XBOX_DEADBAND) {
            val leftOperatorTrigger = OI.operatorGamepad.leftTrigger
            val rightOperatorTrigger = OI.operatorGamepad.rightTrigger
            if(leftOperatorTrigger > rightOperatorTrigger) {
                Arm.armMotionPercentOutput(leftOperatorTrigger, Arm.ArmDirection.UP)
            }
            else {
                Arm.armMotionPercentOutput(rightOperatorTrigger, Arm.ArmDirection.DOWN)
            }
        }
    }
}