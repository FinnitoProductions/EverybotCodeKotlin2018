package frc.robot.commands

import edu.wpi.first.wpilibj.command.CommandGroup

/**
 * Contains all autonomous commands as one command group.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/11/18
 */
class AutonomousCommand : CommandGroup {

    constructor(mode: harkerrobolib.auto.AutoMode) {
        addSequential(mode)
    }


}