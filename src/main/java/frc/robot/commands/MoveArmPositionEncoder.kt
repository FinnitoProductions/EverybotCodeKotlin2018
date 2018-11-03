package frc.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import harkerrobolib.subsystems.HSArm
import harkerrobolib.util.Conversions

/**
 * Moves the arm to a certain position determined by the current spike.
 *
 * @param position the position to which the arm should move
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/4/18
 */
class MoveArmPositionEncoder(val position: Double) : Command() {

    init {
        requires(Arm)
    }

    override fun initialize() {
        Arm.talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY, Global.TIMEOUT)

        Arm.talon.selectProfileSlot(Arm.POSITION_PID_SLOT, Global.PID_PRIMARY)

        Arm.talon.setSensorPhase(Arm.ENCODER_PHASE)
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
//        Arm.talon[ControlMode.Position] = position
        SmartDashboard.putNumber("Arm Position", Arm.talon.getSelectedSensorPosition(Global.PID_PRIMARY).toDouble())//Arm.armMotionPercentOutput(speed * if (direction == HSArm.ArmDirection.UP) 1 else -1)
    }
}