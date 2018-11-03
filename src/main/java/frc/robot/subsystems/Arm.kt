package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Command
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.MoveArmManual
import harkerrobolib.subsystems.HSArm
import harkerrobolib.util.MathUtil
import harkerrobolib.wrappers.HSTalon

/**
 * Represents the arm on the robot with a single Talon for control and position movement based on current spikes.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/4/18
 */
object Arm : HSArm(HSTalon(CAN_IDs.ARM_TALON_ID), 0.05) {

    const val INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake

    const val TALON_PEAK_CURRENT = 17
    const val TALON_CONTINUOUS_CURRENT = 10
    const val TALON_PEAK_TIME = 1000
    const val TALON_CURRENT_ENABLE = true
    const val MAX_MANUAL_SPEED = 1.0
    const val TALON_MOTION_DIRECTION = 1
    const val TALON_CURRENT_SPIKE = 2.6
    const val MAX_POSITION_UP_SPEED = 0.7
    const val MAX_POSITION_DOWN_SPEED = 0.6
    const val FEED_FORWARD_GRAV = 0.05
    const val ENCODER_PHASE = false
    const val POSITION_PID_SLOT = 0
    const val MAX_UP_POSITION = 1000
    const val MAX_DOWN_POSITION = 0




    val DEFAULT_COMMAND = MoveArmManual()

    enum class ArmDirection {
        UP, DOWN
    }


    override fun initDefaultCommand() {
        defaultCommand = DEFAULT_COMMAND
    }


    fun talonInit() {
        invertTalons()
        setNeutralModes()
        setCurrentLimits(TALON_PEAK_TIME, TALON_PEAK_CURRENT, TALON_CONTINUOUS_CURRENT)
        configSoftLimit()
    }

    private fun invertTalons() {
        talon.inverted = INVERTED
    }

    private fun setNeutralModes() {
        talon.setNeutralMode(TALON_NEUTRAL_MODE)
    }

    private fun configSoftLimit() {
        talon.configForwardSoftLimitThreshold(MAX_UP_POSITION)
        talon.configForwardSoftLimitEnable(true)
        talon.configReverseSoftLimitThreshold(MAX_DOWN_POSITION)
        talon.configReverseSoftLimitEnable(true)
    }
}