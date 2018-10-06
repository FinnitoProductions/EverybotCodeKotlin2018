package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.ArmConstants
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Arm
import frc.robot.util.MathUtil

class MoveArmManual() : Command() {

    init {
        requires(Arm)
    }
    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        var joystickInput = MathUtil.mapOutput(OI.gamepad.rightTrigger, Global.DEADBAND)
        if(Arm.getTalonCurrent() >= ArmConstants.TALON_CURRENT_SPIKE)
            joystickInput = 0.0
        if(Math.signum(joystickInput) as Int == OI.JOYSTICK_UP)
            Arm.armMotionPercentOutput(joystickInput, Arm.ArmDirection.UP)
        else
            Arm.armMotionPercentOutput(joystickInput, Arm.ArmDirection.DOWN)
    }
}