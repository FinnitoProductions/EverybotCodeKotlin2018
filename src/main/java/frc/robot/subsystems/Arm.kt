package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.MoveArmManual
import frc.robot.util.MathUtil

object Arm : Subsystem() {

    val armTalon: TalonSRX = TalonSRX(CAN_IDs.ARM_TALON_ID)

    const val ARM_INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake
    const val TALON_PEAK_CURRENT = 0
    const val TALON_CONTINUOUS_CURRENT = 0
    const val TALON_PEAK_TIME = 0
    const val TALON_CURRENT_ENABLE = true
    const val MAX_MOTION_SPEED = 1.0
    const val MIN_MOTION_SPEED = 0.0
    const val TALON_MOTION_DIRECTION = 1
    const val TALON_CURRENT_SPIKE = 0.0
    const val MAX_EXTREME_SPEED = 1.0

    enum class ArmDirection {
        UP, DOWN
    }


    override fun initDefaultCommand() {
        defaultCommand = MoveArmManual()
    }


    fun talonInit() {
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
    }

    private fun invertTalons() {
        armTalon.inverted = ARM_INVERTED
    }

    private fun setNeutralModes() {
        armTalon.setNeutralMode(TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        armTalon.configPeakCurrentDuration(TALON_PEAK_TIME, Global.TIMEOUT)
        armTalon.configPeakCurrentLimit(TALON_PEAK_CURRENT, Global.TIMEOUT)
        armTalon.configContinuousCurrentLimit(TALON_CONTINUOUS_CURRENT, Global.TIMEOUT)
        armTalon.enableCurrentLimit(TALON_CURRENT_ENABLE)

    }

    /**
     * Sets armTalon's percent output based on a specified direction.
     *
     * @param output percent at which motor will turn
     * @param direction direction arm will move
     */
    fun armMotionPercentOutput(output: Double, direction: ArmDirection) {
        val modifiedOutput = MathUtil.constrainOutput(output, MAX_MOTION_SPEED, MIN_MOTION_SPEED)
        if (direction == ArmDirection.UP) {
            armTalon.set(ControlMode.PercentOutput, modifiedOutput * TALON_MOTION_DIRECTION)
        } else {
            armTalon.set(ControlMode.PercentOutput, modifiedOutput * -TALON_MOTION_DIRECTION)
        }
    }

    fun getTalonCurrent() = armTalon.outputCurrent

}