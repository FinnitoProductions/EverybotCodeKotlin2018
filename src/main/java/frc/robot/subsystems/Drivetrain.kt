package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.DriveWithVelocityManual


object Drivetrain : Subsystem() {
    val leftMaster: TalonSRX
    val rightMaster: TalonSRX
    val leftFollower: TalonSRX
    val rightFollower: TalonSRX

    object DrivetrainConstants {

        const val LEFT_MASTER_INVERTED = true
        const val RIGHT_MASTER_INVERTED = true
        const val LEFT_FOLLOWER_INVERTED = true
        const val RIGHT_FOLLOWER_INVERTED = true
        var TALON_NEUTRAL_MODE = NeutralMode.Brake
        const val TALON_PEAK_RIGHT_CURRENT = 50
        const val TALON_PEAK_LEFT_CURRENT = 50
        const val TALON_CONTINUOUS_RIGHT_CURRENT = 20
        const val TALON_CONTINUOUS_LEFT_CURRENT = 20
        const val TALON_PEAK_TIME = 500
        const val TALON_CURRENT_ENABLE = true
    }

    init {
        leftMaster = TalonSRX(CAN_IDs.LEFT_MASTER_ID)
        rightMaster = TalonSRX(CAN_IDs.RIGHT_MASTER_ID)
        leftFollower = TalonSRX(CAN_IDs.LEFT_FOLLOWER_ID)
        rightFollower = TalonSRX(CAN_IDs.RIGHT_FOLLOWER_ID)
        OI.initBindings()
    }

    fun talonInit() {
        followMasters()
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
    }

    private fun followMasters() {
        leftFollower.follow(leftMaster)
        rightFollower.follow(rightMaster)
    }

    private fun invertTalons() {
        leftMaster.inverted = DrivetrainConstants.LEFT_MASTER_INVERTED
        rightMaster.inverted = DrivetrainConstants.RIGHT_MASTER_INVERTED
        leftFollower.inverted = DrivetrainConstants.LEFT_FOLLOWER_INVERTED
        rightFollower.inverted = DrivetrainConstants.RIGHT_FOLLOWER_INVERTED
    }

    private fun setNeutralModes() {
        leftMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE)
        rightMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE)
        leftFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE)
        rightFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        leftMaster.configPeakCurrentDuration(DrivetrainConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        leftMaster.configPeakCurrentLimit(DrivetrainConstants.TALON_PEAK_LEFT_CURRENT, Global.TIMEOUT)
        leftMaster.configContinuousCurrentLimit(DrivetrainConstants.TALON_CONTINUOUS_LEFT_CURRENT, Global.TIMEOUT)
        leftMaster.enableCurrentLimit(DrivetrainConstants.TALON_CURRENT_ENABLE)
        rightMaster.configPeakCurrentDuration(DrivetrainConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        rightMaster.configPeakCurrentLimit(DrivetrainConstants.TALON_PEAK_RIGHT_CURRENT, Global.TIMEOUT)
        rightMaster.configContinuousCurrentLimit(DrivetrainConstants.TALON_CONTINUOUS_RIGHT_CURRENT, Global.TIMEOUT)
        rightMaster.enableCurrentLimit(DrivetrainConstants.TALON_CURRENT_ENABLE)

    }

    fun arcadeDrivePercentOutput(speed: Double, turn: Double) {
        val divisor = Math.max(1.0, Math.max(Math.abs(speed + Math.pow(turn, 2.0)), Math.abs(speed - Math.pow(turn, 2.0))))
        val leftOutputBase = speed + turn * Math.abs(turn)
        leftMaster.set(ControlMode.PercentOutput, leftOutputBase / divisor)
        val rightOutputBase = speed - turn * Math.abs(turn)
        rightMaster.set(ControlMode.PercentOutput,
                rightOutputBase / divisor)
    }

    override fun initDefaultCommand() {
        defaultCommand = DriveWithVelocityManual()
    }
}