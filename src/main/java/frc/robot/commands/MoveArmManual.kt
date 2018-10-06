package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
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
        var output = MathUtil.mapOutput(OI.gamepad.rightTrigger, Global.DEADBAND)
        if(output > 0)
            Arm.armMotionPercentOutput(output, Arm.ArmDirection.UP)
        else
            Arm.armMotionPercentOutput(output, Arm.ArmDirection.DOWN)
    }
}