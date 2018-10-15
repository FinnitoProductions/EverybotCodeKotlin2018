package frc.robot

import frc.robot.commands.IntakeOuttakeIndefinite
import frc.robot.commands.MoveArmPosition
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Intake
import harkerrobolib.wrappers.GamepadWrapper
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
        driverGamepad.buttonA.whenPressed(MoveArmPosition(Arm.MAX_EXTREME_SPEED, Arm.ArmDirection.DOWN))
        driverGamepad.buttonY.whenPressed(MoveArmPosition(Arm.MAX_EXTREME_SPEED, Arm.ArmDirection.UP))

        //operator arm buttons
        operatorGamepad.buttonA.whenPressed(MoveArmPosition(Arm.MAX_EXTREME_SPEED, Arm.ArmDirection.DOWN))
        operatorGamepad.buttonY.whenPressed(MoveArmPosition(Arm.MAX_EXTREME_SPEED, Arm.ArmDirection.UP))

        //operator intake and outtake buttons
        val intakeCommand = IntakeOuttakeIndefinite(Intake.DEFAULT_INTAKE_SPEED, Intake.IntakeDirection.IN)
        operatorGamepad.buttonB.whenPressed(intakeCommand)
        operatorGamepad.buttonB.cancelWhenReleased(intakeCommand)
        val outtakeCommand = IntakeOuttakeIndefinite(Intake.DEFAULT_OUTTAKE_SPEED, Intake.IntakeDirection.OUT)
        operatorGamepad.buttonX.whenPressed(outtakeCommand)
        operatorGamepad.buttonX.cancelWhenReleased(outtakeCommand)

    }
}