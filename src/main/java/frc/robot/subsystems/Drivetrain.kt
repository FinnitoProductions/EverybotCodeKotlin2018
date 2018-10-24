package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.ArcadeDriveVelocity


object Drivetrain : Subsystem() {
    val leftMaster: TalonSRX
    val rightMaster: TalonSRX
    val leftFollower: TalonSRX
    val rightFollower: TalonSRX

    //Talon constants
    const val LEFT_MASTER_INVERTED = true
    const val RIGHT_MASTER_INVERTED = true
    const val LEFT_FOLLOWER_INVERTED = true
    const val RIGHT_FOLLOWER_INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake
    const val TALON_PEAK_RIGHT_CURRENT = 50
    const val TALON_PEAK_LEFT_CURRENT = 50
    const val TALON_CONTINUOUS_RIGHT_CURRENT = 20
    const val TALON_CONTINUOUS_LEFT_CURRENT = 20
    const val TALON_PEAK_TIME = 500
    const val TALON_CURRENT_ENABLE = true

    //PID constants
    const val  PID_PRIMARY = 0;
    const val PID_AUXILIARY = 1;
    const val KP_POSITION_LEFT = 0.0
    const val KI_POSITION_LEFT = 0.0
    const val KD_POSITION_LEFT = 0.0
    const val KF_POSITION_LEFT = 0.0

    const val KP_POSITION_RIGHT = 0.0
    const val KI_POSITION_RIGHT = 0.0
    const val KD_POSITION_RIGHT = 0.0
    const val KF_POSITION_RIGHT = 0.0

    const val POSITION_PID_SLOT = 0

    const val ALLOWABLE_POSITION_ERROR = 1.0

    const val LEFT_ENCODER_PHASE = false
    const val RIGHT_ENCODER_PHASE = false

    const val INITIAL_POSITION = 0


    init {
        leftMaster = TalonSRX(CAN_IDs.LEFT_MASTER_ID)
        rightMaster = TalonSRX(CAN_IDs.RIGHT_MASTER_ID)
        leftFollower = TalonSRX(CAN_IDs.LEFT_FOLLOWER_ID)
        rightFollower = TalonSRX(CAN_IDs.RIGHT_FOLLOWER_ID)
    }

    fun talonInit() {
        followMasters()
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
        setUpPositionPID()
    }

    private fun followMasters() {
        leftFollower.follow(leftMaster)
        rightFollower.follow(rightMaster)
    }

    private fun invertTalons() {
        leftMaster.inverted = LEFT_MASTER_INVERTED
        rightMaster.inverted = RIGHT_MASTER_INVERTED
        leftFollower.inverted = LEFT_FOLLOWER_INVERTED
        rightFollower.inverted = RIGHT_FOLLOWER_INVERTED
    }

    private fun setNeutralModes() {
        leftMaster.setNeutralMode(TALON_NEUTRAL_MODE)
        rightMaster.setNeutralMode(TALON_NEUTRAL_MODE)
        leftFollower.setNeutralMode(TALON_NEUTRAL_MODE)
        rightFollower.setNeutralMode(TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        leftMaster.configPeakCurrentDuration(TALON_PEAK_TIME, Global.TIMEOUT)
        leftMaster.configPeakCurrentLimit(TALON_PEAK_LEFT_CURRENT, Global.TIMEOUT)
        leftMaster.configContinuousCurrentLimit(TALON_CONTINUOUS_LEFT_CURRENT, Global.TIMEOUT)
        leftMaster.enableCurrentLimit(TALON_CURRENT_ENABLE)
        rightMaster.configPeakCurrentDuration(TALON_PEAK_TIME, Global.TIMEOUT)
        rightMaster.configPeakCurrentLimit(TALON_PEAK_RIGHT_CURRENT, Global.TIMEOUT)
        rightMaster.configContinuousCurrentLimit(TALON_CONTINUOUS_RIGHT_CURRENT, Global.TIMEOUT)
        rightMaster.enableCurrentLimit(TALON_CURRENT_ENABLE)

    }

    fun arcadeDrivePercentOutput(speed: Double, turn: Double) {
        val divisor = Math.max(1.0, Math.max(Math.abs(speed + Math.pow(turn, 2.0)), Math.abs(speed - Math.pow(turn, 2.0))))
        val leftOutputBase = speed + turn * Math.abs(turn)
        leftMaster[ControlMode.PercentOutput] = leftOutputBase / divisor
        val rightOutputBase = speed - turn * Math.abs(turn)
        rightMaster[ControlMode.PercentOutput] = rightOutputBase / divisor
    }

    fun tankDrivePercentOutput(leftOutput: Double, rightOutput: Double) {
        val speed = (leftOutput + rightOutput)/2
        val turn = (leftOutput - rightOutput)/2
        arcadeDrivePercentOutput(speed, turn)
    }

    private fun setUpPositionPID() {
        leftMaster.config_kP(POSITION_PID_SLOT, KP_POSITION_LEFT, Global.TIMEOUT)
        leftMaster.config_kI(POSITION_PID_SLOT, KI_POSITION_LEFT, Global.TIMEOUT)
        leftMaster.config_kD(POSITION_PID_SLOT, KD_POSITION_LEFT, Global.TIMEOUT)
        leftMaster.config_kF(POSITION_PID_SLOT, KF_POSITION_LEFT, Global.TIMEOUT)

        rightMaster.config_kP(POSITION_PID_SLOT, KP_POSITION_RIGHT, Global.TIMEOUT)
        rightMaster.config_kI(POSITION_PID_SLOT, KI_POSITION_RIGHT, Global.TIMEOUT)
        rightMaster.config_kD(POSITION_PID_SLOT, KD_POSITION_RIGHT, Global.TIMEOUT)
        rightMaster.config_kF(POSITION_PID_SLOT, KF_POSITION_RIGHT, Global.TIMEOUT)
    }
    override fun initDefaultCommand() {
        defaultCommand = ArcadeDriveVelocity()
    }
}