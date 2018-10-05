package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.subsystems.Arm

class MoveArmManual() : Command() {

    init {
        requires(Arm)
    }
    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {

    }
}