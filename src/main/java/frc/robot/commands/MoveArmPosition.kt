package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.ArmConstants
import frc.robot.subsystems.Arm

class MoveArmPosition(val speed: Double, val direction: Arm.ArmDirection) : Command() {

    init {
        requires(Arm)
    }
    override fun isFinished(): Boolean {
        return Arm.getTalonCurrent() >= ArmConstants.TALON_CURRENT_SPIKE
    }

    override fun execute() {
        Arm.armMotionPercentOutput(speed, direction)
    }
}