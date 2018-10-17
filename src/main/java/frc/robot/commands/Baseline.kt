package frc.robot.commands

import frc.robot.auto.AutoMode

class Baseline : AutoMode() {


    override fun addCommands() {
        this.addSequential(DriveToPosition(DISTANCE))
    }

    companion object {
        const val DISTANCE = 15.0
    }

}