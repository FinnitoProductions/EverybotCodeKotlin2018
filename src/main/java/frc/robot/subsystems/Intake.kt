package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.CAN_IDs
import frc.robot.Global
import frc.robot.commands.IntakeOuttakeManual
import harkerrobolib.subsystems.HSIntake
import harkerrobolib.util.MathUtil
import harkerrobolib.wrappers.HSTalon

/**
 * Represents the intake on the robot with a two Talons for left and right control.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/1/18
 */
object Intake : HSIntake(HSTalon(CAN_IDs.LEFT_INTAKE_ID), HSTalon(CAN_IDs.RIGHT_INTAKE_ID) ) {

    const val LEFT_TALON_INVERTED = false
    const val RIGHT_TALON_INVERTED = true
    val TALON_NEUTRAL_MODE = NeutralMode.Brake
    const val TALON_PEAK_CURRENT = 0
    const val TALON_CONTINUOUS_CURRENT = 0
    const val TALON_PEAK_TIME = 0
    const val TALON_CURRENT_ENABLE = true
    const val MAX_OUTTAKE_SPEED = 1.0
    const val MIN_OUTTAKE_SPEED = 0.0
    const val TALON_INTAKE_DIRECTION = 1
    const val DEFAULT_INTAKE_SPEED = 1.0
    const val DEFAULT_OUTTAKE_SPEED = 1.0

    val DEFAULT_COMMAND = IntakeOuttakeManual()

    fun talonInit() {
        invertTalons()
        setNeutralModes(NeutralMode.Brake)
        setCurrentLimits(TALON_PEAK_TIME, TALON_PEAK_CURRENT, TALON_CONTINUOUS_CURRENT)
    }

    private fun invertTalons() {
        leftTalon.inverted = Intake.LEFT_TALON_INVERTED
        rightTalon.inverted = Intake.RIGHT_TALON_INVERTED

    }

    override fun initDefaultCommand() {
        defaultCommand = DEFAULT_COMMAND
    }


}
