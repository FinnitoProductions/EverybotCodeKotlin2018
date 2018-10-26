package frc.robot.commands

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Arm
import harkerrobolib.util.Conversions

class MoveArmPosition(val speed: Double, val direction: Arm.ArmDirection) : Command() {
    val startTime : Double

    init {
        requires(Arm)
        startTime = Timer.getFPGATimestamp()
    }

    override fun isFinished(): Boolean {
        return (Timer.getFPGATimestamp() - startTime) > Conversions.convertTime(Conversions.TimeUnit.MILLISECONDS,  Arm.TALON_PEAK_TIME.toDouble(),  Conversions.TimeUnit.SECONDS) && Arm.getTalonCurrent() >= Arm.TALON_CURRENT_SPIKE || Math.abs(OI.driverGamepad.rightTrigger) < OI.XBOX_DEADBAND
    }

    override fun execute() {
        Arm.armMotionPercentOutput(speed, direction)
    }
}