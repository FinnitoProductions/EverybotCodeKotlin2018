package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Command
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.MoveArmManual
import harkerrobolib.util.MathUtil

/**
 * Represents the arm on the robot with a single Talon for control and position movement based on current spikes.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/4/18
 */
object Arm : Subsystem() {

    val armTalon: TalonSRX = TalonSRX(CAN_IDs.ARM_TALON_ID)

    const val INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake

    const val TALON_PEAK_CURRENT = 10
    const val TALON_CONTINUOUS_CURRENT = 5
    const val TALON_PEAK_TIME = 1000
    const val TALON_CURRENT_ENABLE = true
    const val MAX_MANUAL_SPEED = 0.3
    const val TALON_MOTION_DIRECTION = 10
    const val TALON_CURRENT_SPIKE = 3
    const val MAX_POSITION_UP_SPEED = 0.6
    const val MAX_POSITION_DOWN_SPEED = 0.07
    const val FEED_FORWARD_GRAV = 0.05

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
        setCurrentLimits()
    }

    private fun invertTalons() {
        armTalon.inverted = INVERTED
    }

    private fun setNeutralModes() {
        armTalon.setNeutralMode(TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        with (armTalon) {
            configPeakCurrentDuration(TALON_PEAK_TIME, Global.TIMEOUT)
            configPeakCurrentLimit(TALON_PEAK_CURRENT, Global.TIMEOUT)
            configContinuousCurrentLimit(TALON_CONTINUOUS_CURRENT, Global.TIMEOUT)
            enableCurrentLimit(TALON_CURRENT_ENABLE)
        }

    }

    /**
     * Sets armTalon's percent output based on a specified direction.
     *
     * @param output percent at which motor will turn
     * @param direction direction arm will move
     */
    fun armMotionPercentOutput(output: Double, direction: ArmDirection) {
        val modifiedOutput = output//MathUtil.constrain(output, MAX_MOTION_SPEED, MIN_MOTION_SPEED)
        if (direction == ArmDirection.UP) {
            armMotionPercentOutput(modifiedOutput * TALON_MOTION_DIRECTION)
        } else {
            armMotionPercentOutput(modifiedOutput * -TALON_MOTION_DIRECTION)
        }
    }

    fun armMotionPercentOutput (output: Double) {
        armTalon.set(ControlMode.PercentOutput, output, DemandType.ArbitraryFeedForward, Arm.FEED_FORWARD_GRAV)
    }

    fun getTalonCurrent() = armTalon.outputCurrent

}