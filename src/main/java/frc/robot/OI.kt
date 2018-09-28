package frc.robot

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

object OI {
    val GAMEPAD_PORT = 0
    var gamepad = GamepadWrapper(GAMEPAD_PORT)

}