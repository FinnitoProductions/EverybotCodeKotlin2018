package frc.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.ControlMode.Position
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Command
import frc.robot.Global
import frc.robot.subsystems.Drivetrain

class DriveToPosition(val position: Double) : Command() {

    var currentPosition = 0

    init {
        Drivetrain.leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative ,Drivetrain.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Drivetrain.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.leftMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Drivetrain.PID_PRIMARY)
        Drivetrain.rightMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Drivetrain.PID_PRIMARY)
        Drivetrain.leftMaster.setSensorPhase(Drivetrain.LEFT_PHASE)
        Drivetrain.rightMaster.setSensorPhase(Drivetrain.RIGHT_PHASE)
        Drivetrain.leftMaster.setSelectedSensorPosition(Drivetrain.INITIAL_POSITION, Drivetrain.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.setSelectedSensorPosition(Drivetrain.INITIAL_POSITION, Drivetrain.PID_PRIMARY, Global.TIMEOUT)
    }

    override fun isFinished(): Boolean {
        return Math.abs(position - Drivetrain.leftMaster.getSelectedSensorPosition(Drivetrain.PID_PRIMARY)) < Drivetrain.ALLOWABLE_POSITION_ERROR &&
                Math.abs(position - Drivetrain.rightMaster.getSelectedSensorPosition(Drivetrain.PID_PRIMARY)) < Drivetrain.ALLOWABLE_POSITION_ERROR
    }

    override fun execute() {
        Drivetrain.leftMaster.set(Position, position)
    }
}