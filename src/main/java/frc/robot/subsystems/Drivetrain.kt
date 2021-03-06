package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import frc.robot.commands.ArcadeDriveVelocity
import harkerrobolib.subsystems.HSDrivetrain
import harkerrobolib.util.Conversions
import harkerrobolib.wrappers.HSTalon

/**
 * Represents the drivetrain on the robot with two master Talons and two follower Talons and functionality
 * for arcade and tank driving modes.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 9/27/18
 */
object Drivetrain : HSDrivetrain(HSTalon(CAN_IDs.LEFT_MASTER_ID), HSTalon(CAN_IDs.RIGHT_MASTER_ID) , HSTalon(CAN_IDs.LEFT_FOLLOWER_ID) , HSTalon(CAN_IDs.RIGHT_FOLLOWER_ID)) {


    //Talon constants
    const val LEFT_MASTER_INVERTED = true
    const val RIGHT_MASTER_INVERTED = false
    const val LEFT_FOLLOWER_INVERTED = true
    const val RIGHT_FOLLOWER_INVERTED = false
    val TALON_NEUTRAL_MODE = NeutralMode.Brake
    //const val TALON_PEAK_RIGHT_CURRENT = 50
    //const val TALON_PEAK_LEFT_CURRENT = 50
    const val TALON_PEAK_CURRENT = 50
    //const val TALON_CONTINUOUS_RIGHT_CURRENT = 20
    //const val TALON_CONTINUOUS_LEFT_CURRENT = 20
    const val TALON_CONTINUOUS_CURRENT = 20
    const val TALON_PEAK_TIME = 500
    const val TALON_CURRENT_ENABLE = true

    const val KP_POSITION_LEFT = 0.0
    const val KI_POSITION_LEFT = 0.0
    const val KD_POSITION_LEFT = 0.0
    const val KF_POSITION_LEFT = 0.0

    const val KP_POSITION_RIGHT = 0.0
    const val KI_POSITION_RIGHT = 0.0
    const val KD_POSITION_RIGHT = 0.0
    const val KF_POSITION_RIGHT = 0.0

    const val KP_MOTION_MAGIC_LEFT = 0.0
    const val KI_MOTION_MAGIC_LEFT = 0.0
    const val KD_MOTION_MAGIC_LEFT = 0.0
    const val KF_MOTION_MAGIC_LEFT = 0.0

    const val KP_MOTION_MAGIC_RIGHT = 0.0
    const val KI_MOTION_MAGIC_RIGHT = 0.0
    const val KD_MOTION_MAGIC_RIGHT = 0.0
    const val KF_MOTION_MAGIC_RIGHT = 0.0

    const val CRUISE_VELOCITY_MOTION_MAGIC = 0.0
    const val ACCELERATION_MOTION_MAGIC = 0.0

    const val POSITION_PID_SLOT = 0
    const val MOTION_MAGIC_PID_SLOT = 1

    const val ALLOWABLE_POSITION_ERROR = 1.0

    const val LEFT_ENCODER_PHASE = false
    const val RIGHT_ENCODER_PHASE = false


    val DEFAULT_COMMAND = ArcadeDriveVelocity()

    enum class TurnDirection {
        LEFT, RIGHT
    }

    fun talonInit() {
        followMasters()
        invertTalons()
        setNeutralMode(TALON_NEUTRAL_MODE)
        setCurrentLimit(TALON_PEAK_CURRENT, TALON_PEAK_TIME, TALON_CONTINUOUS_CURRENT)
        setUpPositionPID()
        configMotionMagic()
    }

    private fun invertTalons() {
        leftMaster.inverted = LEFT_MASTER_INVERTED
        rightMaster.inverted = RIGHT_MASTER_INVERTED
        leftFollower?.inverted = LEFT_FOLLOWER_INVERTED
        rightFollower?.inverted = RIGHT_FOLLOWER_INVERTED
    }

    fun arcadeDrivePercentOutput(speed: Double, turn: Double) {
        val divisor = Math.max(1.0, Math.max(Math.abs(speed + Math.pow(turn, 2.0)), Math.abs(speed - Math.pow(turn, 2.0))))
        val leftOutputBase = speed + turn * Math.abs(turn)
        leftMaster[ControlMode.PercentOutput] = leftOutputBase / divisor
        val rightOutputBase = speed - turn * Math.abs(turn)
        rightMaster[ControlMode.PercentOutput] = rightOutputBase / divisor
    }

    fun tankDrivePercentOutput(leftOutput: Double, rightOutput: Double) {
        // val speed = (leftOutput + rightOutput)/2
        // val turn = (leftOutput - rightOutput)/2
        // arcadeDrivePercentOutput(speed, turn)
        leftMaster.set(ControlMode.PercentOutput, leftOutput)
        rightMaster.set(ControlMode.PercentOutput, rightOutput)

    }

    private fun setUpPositionPID() {
        val setupPID = { talon: TalonSRX, kF: Double, kP: Double, kI: Double, kD: Double ->
            talon.config_kF(POSITION_PID_SLOT, kF, Global.TIMEOUT)
            talon.config_kP(POSITION_PID_SLOT, kP, Global.TIMEOUT)
            talon.config_kI(POSITION_PID_SLOT, kI, Global.TIMEOUT)
            talon.config_kD(POSITION_PID_SLOT, kD, Global.TIMEOUT)
        }

        setupPID.invoke(leftMaster, Drivetrain.KF_POSITION_LEFT, Drivetrain.KP_POSITION_LEFT, Drivetrain.KI_POSITION_LEFT, Drivetrain.KD_POSITION_LEFT)
        setupPID.invoke(rightMaster, Drivetrain.KF_POSITION_RIGHT, Drivetrain.KP_POSITION_RIGHT, Drivetrain.KI_POSITION_RIGHT, Drivetrain.KD_POSITION_RIGHT)
    }

    override fun initDefaultCommand() {
        defaultCommand = DEFAULT_COMMAND
    }

    fun configMotionMagic() {
        with(leftMaster) {
            config_kF(MOTION_MAGIC_PID_SLOT, KF_MOTION_MAGIC_LEFT)
            config_kP(MOTION_MAGIC_PID_SLOT, KP_MOTION_MAGIC_LEFT)
            config_kI(MOTION_MAGIC_PID_SLOT, KI_MOTION_MAGIC_LEFT)
            config_kD(MOTION_MAGIC_PID_SLOT, KD_MOTION_MAGIC_LEFT)
            configMotionCruiseVelocity(CRUISE_VELOCITY_MOTION_MAGIC.toInt())
            configMotionAcceleration(ACCELERATION_MOTION_MAGIC.toInt())
        }
        with(rightMaster) {
            config_kF(MOTION_MAGIC_PID_SLOT, KF_MOTION_MAGIC_RIGHT)
            config_kP(MOTION_MAGIC_PID_SLOT, KP_MOTION_MAGIC_RIGHT)
            config_kI(MOTION_MAGIC_PID_SLOT, KI_MOTION_MAGIC_RIGHT)
            config_kD(MOTION_MAGIC_PID_SLOT, KD_MOTION_MAGIC_RIGHT)
            configMotionCruiseVelocity(CRUISE_VELOCITY_MOTION_MAGIC.toInt())
            configMotionAcceleration(ACCELERATION_MOTION_MAGIC.toInt())
        }
    }
}