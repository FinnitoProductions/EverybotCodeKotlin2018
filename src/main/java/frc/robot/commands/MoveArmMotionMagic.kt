package frc.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.command.Command
import frc.robot.Global
import frc.robot.subsystems.Arm

/**
 * Moves arm using motion magic to a specified position.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 *
 * @version 11/6/18
 */
class MoveArmMotionMagic(val position : Int) : Command() {

    override fun isFinished() = Math.abs(Arm.talon.getClosedLoopError(Global.PID_PRIMARY)) <= Arm.MOTION_MAGIC_ALLOWABLE_ERROR

    override fun initialize() {
        with (Arm.talon) {
            configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY)
            setSensorPhase(Arm.ENCODER_PHASE)
            selectProfileSlot(Arm.MOTION_MAGIC_SLOT, Global.PID_PRIMARY)
        }
    }

    override fun execute() {
        Arm.talon.set(ControlMode.MotionMagic, position.toDouble())
    }

}