package frc.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Global
import frc.robot.subsystems.Drivetrain
import harkerrobolib.util.Conversions

/**
 *  Drives the robot to a specified position using PID.
 *
 *  @param  position    setpoint in feet
 *
 *  @author Angela Jia
 *  @author Finn Frankis
 *  @version    10/14/18
 */
class DriveToPosition(val position: Double) : Command() {

    init {
        requires(Drivetrain)
    }

    val convertedPosition = Conversions.convertPosition(Conversions.PositionUnit.FEET,position, Conversions.PositionUnit.ENCODER_UNITS)

    override fun initialize() {
        Drivetrain.leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY, Global.TIMEOUT)

        Drivetrain.leftMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Global.PID_PRIMARY)
        Drivetrain.rightMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Global.PID_PRIMARY)

        Drivetrain.leftMaster.setSensorPhase(Drivetrain.LEFT_ENCODER_PHASE)
        Drivetrain.rightMaster.setSensorPhase(Drivetrain.RIGHT_ENCODER_PHASE)

        Drivetrain.leftMaster.setSelectedSensorPosition(0, Global.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.setSelectedSensorPosition(0, Global.PID_PRIMARY, Global.TIMEOUT)
    }

    override fun isFinished(): Boolean {
        return false;//Math.abs(position - Drivetrain.leftMaster.getSelectedSensorPosition(Global.PID_PRIMARY)) < Drivetrain.ALLOWABLE_POSITION_ERROR &&
                //Math.abs(position - Drivetrain.rightMaster.getSelectedSensorPosition(Global.PID_PRIMARY)) < Drivetrain.ALLOWABLE_POSITION_ERROR
    }

    override fun execute() {
//        Drivetrain.leftMaster[ControlMode.Position] = convertedPosition
//       Drivetrain.rightMaster[ControlMode.Position] = convertedPosition
        SmartDashboard.putNumber("left pos", Drivetrain.leftMaster.getSelectedSensorPosition(Global.PID_PRIMARY).toDouble())
        SmartDashboard.putNumber("right pos", Drivetrain.rightMaster.getSelectedSensorPosition(Global.PID_PRIMARY).toDouble())
    }
}