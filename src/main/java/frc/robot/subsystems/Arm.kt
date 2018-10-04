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
        armTalon.configPeakCurrentDuration()
        armTalon.configPeakCurrentLimit()
        armTalon.configContinuousCurrentLimit()
        armTalon.enableCurrentLimit()

    }

    fun arcadeDrivePercentOutput(speed: Double, turn: Double) {
        val divisor = Math.max(1.0, Math.max(Math.abs(speed + Math.pow(turn, 2.0)), Math.abs(speed - Math.pow(turn, 2.0))))
        val leftOutputBase = speed + turn * Math.abs(turn)
        leftMaster.set(ControlMode.PercentOutput, leftOutputBase / divisor)

        val rightOutputBase = speed - turn * Math.abs(turn)
        rightMaster.set(ControlMode.PercentOutput,
                rightOutputBase / divisor)
    }

}