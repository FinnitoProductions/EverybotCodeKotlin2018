package frc.robot

import frc.robot.commands.MoveArmPosition
import frc.robot.subsystems.Arm
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper
import java.awt.Button

object OI() {
    val GAMEPAD_PORT = 0
    val gamepad = GamepadWrapper(GAMEPAD_PORT)
    val JOYSTICK_UP = 1

    init {
        initBindings()
    }
    fun initBindings() {
        OI.gamepad.buttonA.whenPressed(MoveArmPosition(ArmConstants.MIN_POSITION, Arm.ArmDirection.DOWN))
        OI.gamepad.buttonY.whenPressed(MoveArmPosition(ArmConstants.MAX_POSITION, Arm.ArmDirection.UP))

    }
}