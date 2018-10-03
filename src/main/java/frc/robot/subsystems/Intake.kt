package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.IntakeConstants
import frc.robot.CAN_IDs
import frc.robot.Global
import frc.robot.OI

object Intake : Subsystem() {
    val leftTalon: TalonSRX
    val rightTalon: TalonSRX

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
        leftTalon.configContinuousCurrentLimit(IntakeConstants.TALON_CONTINUOUS_LEFT_CURRENT)
        leftTalon.enableCurrentLimit(IntakeConstants.TALON_CURRENT_ENABLE)
        rightTalon.configPeakCurrentDuration(IntakeConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        rightTalon.configPeakCurrentLimit(IntakeConstants.TALON_PEAK_RIGHT_CURRENT, Global.TIMEOUT)
        rightTalon.configContinuousCurrentLimit(IntakeConstants.TALON_CONTINUOUS_RIGHT_CURRENT)
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
            leftTalon.set(ControlMode.PercentOutput, output * IntakeConstants.TALON_INTAKE_DIRECTION)
            rightTalon.set(ControlMode.PercentOutput, output * -IntakeConstants.TALON_INTAKE_DIRECTION)
        } else {
            leftTalon.set(ControlMode.PercentOutput, -output * IntakeConstants.TALON_INTAKE_DIRECTION)
            rightTalon.set(ControlMode.PercentOutput, output * IntakeConstants.TALON_INTAKE_DIRECTION)
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
        if (leftDirection == IntakeDirection.IN) {
            leftTalon.set(ControlMode.PercentOutput, leftOutput * IntakeConstants.TALON_INTAKE_DIRECTION)
        }
        else {
            leftTalon.set(ControlMode.PercentOutput, -leftOutput * IntakeConstants.TALON_INTAKE_DIRECTION)
        }
        if(rightDirection == IntakeDirection.IN) {
            rightTalon.set(ControlMode.PercentOutput, rightOutput * -IntakeConstants.TALON_INTAKE_DIRECTION)
        }
        else {
            rightTalon.set(ControlMode.PercentOutput, rightOutput * IntakeConstants.TALON_INTAKE_DIRECTION)
        }

    }

    override fun initDefaultCommand() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}