package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.DriveWithVelocityManual

object Arm : Subsystem() {

    val armTalon: TalonSRX

    enum class ArmDirection {
        UP, DOWN
    }

    init {
        armTalon = TalonSRX(CAN_IDs.ARM_TALON_ID)
    }


    override fun initDefaultCommand() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun talonInit() {
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
    }

    private fun invertTalons() {
        armTalon.inverted = ArmConstants.ARM_INVERTED
    }

    private fun setNeutralModes() {
        armTalon.setNeutralMode(ArmConstants.TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        armTalon.configPeakCurrentDuration(Global.TIMEOUT)
        armTalon.configPeakCurrentLimit(ArmConstants.TALON_PEAK_CURRENT)
        armTalon.configContinuousCurrentLimit(ArmConstants.TALON_CONTINUOUS_CURRENT)
        armTalon.enableCurrentLimit(ArmConstants.TALON_CURRENT_ENABLE)

    }

    fun armMotionPercentOutput(output: Double, direction: ArmDirection) {
        Ints.constrain
    }

    fun constrainOutput(output: Double, maxSpeed) : Double {
        if(output > IntakeConstants.MAX_OUTTAKE_SPEED) {
            return IntakeConstants.MAX_OUTTAKE_SPEED
        }
        if(output < IntakeConstants.MIN_OUTTAKE_SPEED) {
            return IntakeConstants.MIN_OUTTAKE_SPEED
        }
        return output
    }

}