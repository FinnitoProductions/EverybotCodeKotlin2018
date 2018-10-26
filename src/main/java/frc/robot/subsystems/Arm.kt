package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.MoveArmManual
import harkerrobolib.util.MathUtil

object Arm : Subsystem() {

    val armTalon: TalonSRX = TalonSRX(CAN_IDs.ARM_TALON_ID)

    const val INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake
    const val TALON_PEAK_CURRENT = 20
    const val TALON_CONTINUOUS_CURRENT = 15
    const val TALON_PEAK_TIME = 200
    const val TALON_CURRENT_ENABLE = true
    const val MAX_MOTION_SPEED = 1.0
    const val MIN_MOTION_SPEED = 0.0
    const val TALON_MOTION_DIRECTION = 10
    const val TALON_CURRENT_SPIKE = 0.0
    const val MAX_EXTREME_SPEED = 1.0

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
        //setCurrentLimits()
    }

    private fun invertTalons() {
        armTalon.inverted = INVERTED
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
        val modifiedOutput = output//MathUtil.constrain(output, MAX_MOTION_SPEED, MIN_MOTION_SPEED)
        if (direction == ArmDirection.UP) {
            armMotionPercentOutput(modifiedOutput * TALON_MOTION_DIRECTION)
        } else {
            armMotionPercentOutput(modifiedOutput * -TALON_MOTION_DIRECTION)
        }
    }

    fun armMotionPercentOutput (output: Double) {
        armTalon[ControlMode.PercentOutput] = output
    }

    fun getTalonCurrent() = armTalon.outputCurrent

}