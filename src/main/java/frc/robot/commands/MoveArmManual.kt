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

        var driverJoystickInput = MathUtil.mapJoystickOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
         if (Arm.getTalonCurrent() >= Arm.TALON_CURRENT_SPIKE)
             driverJoystickInput = 0.0
        /*if (Math.signum(driverJoystickInput).toInt() == OI.JOYSTICK_UP)
            Arm.armMotionPercentOutput(driverJoystickInput, Arm.ArmDirection.UP)
        else
            Arm.armMotionPercentOutput(driverJoystickInput, Arm.ArmDirection.DOWN)
        SmartDashboard.putNumber("Right Y", driverJoystickInput)*/
        Arm.armMotionPercentOutput(driverJoystickInput)

        if(Global.HAS_TWO_CONTROLLERS && Math.abs(driverJoystickInput) <= OI.XBOX_DEADBAND) {
            val leftOperatorTrigger = MathUtil.map(OI.operatorGamepad.leftTrigger, 0.0, 1.0,
                    0.0, Arm.MAX_MANUAL_SPEED)
            val rightOperatorTrigger = MathUtil.map(OI.operatorGamepad.rightTrigger, 0.0, 1.0,
                    0.0, Arm.MAX_MANUAL_SPEED)
            if(leftOperatorTrigger > rightOperatorTrigger) {
                Arm.armMotionPercentOutput(leftOperatorTrigger, Arm.ArmDirection.UP)
            }
            else {
                Arm.armMotionPercentOutput(rightOperatorTrigger, Arm.ArmDirection.DOWN)
            }
        }
    }
}