package frc.robot.commands

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Arm
import harkerrobolib.subsystems.HSArm
import harkerrobolib.util.Conversions

/**
 * Moves the arm to a certain position determined by the current spike.
 *
 * @param   speed   rate at which the arm will move
 * @param   direction   specifies if the arm will move up or down
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/4/18
 */
class MoveArmPosition(val speed: Double, val direction: HSArm.ArmDirection) : Command() {
    var startTime : Double

    init {
        requires(Arm)
        startTime = 0.0
    }

    override fun initialize() {
        startTime = Timer.getFPGATimestamp()
    }

    override fun isFinished(): Boolean {
        return (Timer.getFPGATimestamp() - startTime) > 
        Conversions.convertTime(Conversions.TimeUnit.MILLISECONDS,  Arm.TALON_PEAK_TIME.toDouble(), 
         Conversions.TimeUnit.SECONDS) && 
         Arm.getTalonCurrent() >= Arm.TALON_CURRENT_SPIKE || OI.driverGamepad.rightY > OI.XBOX_DEADBAND
    }

    override fun execute() {
        Arm.moveArmPercentOutput(speed * if (direction == HSArm.ArmDirection.UP) -1 else 1)
    }
}