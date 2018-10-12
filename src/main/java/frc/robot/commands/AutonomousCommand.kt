package frc.robot.commands

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.robot.auto.AutoMode

class AutonomousCommand : CommandGroup {

    constructor(mode : AutoMode) {
        addSequential(mode)
    }

}