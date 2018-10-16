package frc.robot.commands

import frc.robot.auto.AutoMode

class Baseline() : AutoMode() {
    override fun addCommands() {
        DriveToPosition(15.0)
    }


}