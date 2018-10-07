package frc.robot

import frc.robot.commands.MoveArmPosition
import frc.robot.subsystems.Arm
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

object OI() {
    val DRIVER_PORT = 0
    val OPERATOR_PORT = 1
    val driverGamepad = GamepadWrapper(DRIVER_PORT)
    val operatorGamepad = GamepadWrapper(OPERATOR_PORT)
    val JOYSTICK_UP = 1

    init {
        initBindings()
    }
    fun initBindings() {
        OI.operatorGamepad.buttonA.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.DOWN))
        OI.operatorGamepad.buttonY.whenPressed(MoveArmPosition(ArmConstants.MAX_EXTREME_SPEED, Arm.ArmDirection.UP))
    }
}