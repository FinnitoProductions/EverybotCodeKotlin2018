package frc.robot

import frc.robot.commands.IntakeOuttakeIndefinite
import frc.robot.commands.MoveArmPosition
import frc.robot.subsystems.Arm
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

object OI {
    val DRIVER_PORT = 0
    val OPERATOR_PORT = 1
    val driverGamepad = GamepadWrapper(DRIVER_PORT)
    val operatorGamepad = GamepadWrapper(OPERATOR_PORT)
    val JOYSTICK_UP = 1

    val XBOX_DEADBAND = 0.1
    val LOGITECH_DEADBAND = 0.1

    init {
        initBindings()
    }

    fun initBindings() {

        //driver arm buttons
        driverGamepad.buttonA.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.DOWN))
        driverGamepad.buttonY.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.UP))

        //operator arm buttons
        operatorGamepad.buttonA.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.DOWN))
        operatorGamepad.buttonY.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.UP))

        //operator intake and outtake buttons
        val intakeCommand = IntakeOuttakeIndefinite(IntakeConstants.DEFAULT_INTAKE_SPEED)
        operatorGamepad.buttonB.whenPressed(intakeCommand)
        operatorGamepad.buttonB.cancelWhenReleased(intakeCommand)
        val outtakeCommand = IntakeOuttakeIndefinite(IntakeConstants.DEFAULT_OUTTAKE_SPEED)
        operatorGamepad.buttonX.whenPressed(outtakeCommand)
        operatorGamepad.buttonX.cancelWhenReleased(outtakeCommand)

    }
}