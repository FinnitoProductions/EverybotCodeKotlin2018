package frc.robot

import com.ctre.phoenix.motorcontrol.NeutralMode


object CAN_IDs {
    const val LEFT_MASTER_ID = -1
    const val RIGHT_MASTER_ID = -1
    const val LEFT_FOLLOWER_ID = -1
    const val RIGHT_FOLLOWER_ID = -1
}

object DrivetrainConstants {

    const val LEFT_MASTER_INVERTED = true
    const val RIGHT_MASTER_INVERTED = true
    const val LEFT_FOLLOWER_INVERTED = true
    const val RIGHT_FOLLOWER_INVERTED = true
    var TALON_NEUTRAL_MODE = NeutralMode.Brake
    const val TALON_PEAK_RIGHT_CURRENT = 50
    const val TALON_CONTINUOUS_RIGHT_CURRENT = 20
    const val TALON_PEAK_LEFT_CURRENT = 50
    const val TALON_CONTINUOUS_LEFT_CURRENT = 20
    const val TALON_PEAK_TIME = 500
    const val TALON_CURRENT_ENABLE = true
}

object Global {
    const val TIMEOUT = 10
    const val DEADBAND = 0.1
}

