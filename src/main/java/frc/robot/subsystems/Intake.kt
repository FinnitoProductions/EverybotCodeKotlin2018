package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.CAN_IDs
import frc.robot.Global
import frc.robot.commands.IntakeOuttakeManual
import frc.robot.util.MathUtil

object Intake : Subsystem() {
    val leftTalon: TalonSRX
    val rightTalon: TalonSRX

    object IntakeConstants {
        const val LEFT_TALON_INVERTED = true
        const val RIGHT_TALON_INVERTED = true
        var TALON_NEUTRAL_MODE = NeutralMode.Brake
        const val TALON_PEAK_RIGHT_CURRENT = 0
        const val TALON_PEAK_LEFT_CURRENT = 0
        const val TALON_CONTINUOUS_LEFT_CURRENT = 0
        const val TALON_CONTINUOUS_RIGHT_CURRENT = 0
        const val TALON_PEAK_TIME = 0
        const val TALON_CURRENT_ENABLE = true
        const val MAX_OUTTAKE_SPEED = 1.0
        const val MIN_OUTTAKE_SPEED = 0.0
        const val TALON_INTAKE_DIRECTION = 1
        const val DEFAULT_INTAKE_SPEED = 1.0
        const val DEFAULT_OUTTAKE_SPEED = -1.0

    }

    enum class IntakeDirection {
        IN, OUT
    }

    init {

        leftTalon = TalonSRX(CAN_IDs.LEFT_INTAKE_ID)
        rightTalon = TalonSRX(CAN_IDs.RIGHT_INTAKE_ID)

    }

    fun talonInit() {
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
    }

    private fun invertTalons() {
        leftTalon.inverted = IntakeConstants.LEFT_TALON_INVERTED
        rightTalon.inverted = IntakeConstants.RIGHT_TALON_INVERTED

    }

    private fun setNeutralModes() {
        leftTalon.setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE)
        rightTalon.setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE)

    }

    private fun setCurrentLimits() {
        leftTalon.configPeakCurrentDuration(IntakeConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        leftTalon.configPeakCurrentLimit(IntakeConstants.TALON_PEAK_LEFT_CURRENT, Global.TIMEOUT)
        leftTalon.configContinuousCurrentLimit(IntakeConstants.TALON_CONTINUOUS_LEFT_CURRENT, Global.TIMEOUT)
        leftTalon.enableCurrentLimit(IntakeConstants.TALON_CURRENT_ENABLE)
        rightTalon.configPeakCurrentDuration(IntakeConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        rightTalon.configPeakCurrentLimit(IntakeConstants.TALON_PEAK_RIGHT_CURRENT, Global.TIMEOUT)
        rightTalon.configContinuousCurrentLimit(IntakeConstants.TALON_CONTINUOUS_RIGHT_CURRENT, Global.TIMEOUT)
        rightTalon.enableCurrentLimit(IntakeConstants.TALON_CURRENT_ENABLE)

    }

    /**
     * Sets wheels to the same output and direction
     *
     * @param output value from controller that sets output for left and right wheels to same value
     * @param direction sets both wheels to either IN or OUT direction
     */
    fun intakeOuttakeCube(output: Double, direction: IntakeDirection) {
        if (direction == IntakeDirection.IN) {
            intakeOuttakeCube(output, output, IntakeDirection.IN, IntakeDirection.IN)
        } else {
            intakeOuttakeCube(output, output, IntakeDirection.OUT, IntakeDirection.OUT)
        }

    }

    /**
     * Sets wheels to their respective outputs and directions.
     *
     * @param leftOutput value from controller that sets left wheel output
     * @param rightOutput value from controller that sets right wheel output
     * @param leftDirection enum representing left wheel's direction
     * @param rightDirection enum representing right wheel's direction
     */
    fun intakeOuttakeCube(leftOutput: Double, rightOutput: Double, leftDirection: IntakeDirection, rightDirection: IntakeDirection) {
        var leftModifiedOutput = MathUtil.constrainOutput(leftOutput, IntakeConstants.MAX_OUTTAKE_SPEED, IntakeConstants.MIN_OUTTAKE_SPEED)
        var rightModifiedOutput = MathUtil.constrainOutput(rightOutput, IntakeConstants.MAX_OUTTAKE_SPEED, IntakeConstants.MIN_OUTTAKE_SPEED)
        if (leftDirection == IntakeDirection.IN) {
            leftTalon.set(ControlMode.PercentOutput, leftModifiedOutput * IntakeConstants.TALON_INTAKE_DIRECTION)
        } else {
            leftTalon.set(ControlMode.PercentOutput, leftModifiedOutput * -IntakeConstants.TALON_INTAKE_DIRECTION)
        }
        if (rightDirection == IntakeDirection.IN) {
            rightTalon.set(ControlMode.PercentOutput, rightModifiedOutput * -IntakeConstants.TALON_INTAKE_DIRECTION)
        } else {
            rightTalon.set(ControlMode.PercentOutput, rightModifiedOutput * IntakeConstants.TALON_INTAKE_DIRECTION)
        }

    }

    override fun initDefaultCommand() {
        defaultCommand = IntakeOuttakeManual()
    }


}