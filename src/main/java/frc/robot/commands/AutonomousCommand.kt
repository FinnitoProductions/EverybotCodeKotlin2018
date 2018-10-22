package frc.robot.commands

import edu.wpi.first.wpilibj.command.CommandGroup

class AutonomousCommand : CommandGroup {

    constructor(mode: harkerrobolib.auto.AutoMode) {
        addSequential(mode)
    }


}