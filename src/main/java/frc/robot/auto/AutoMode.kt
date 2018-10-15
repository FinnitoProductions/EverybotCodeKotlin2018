package frc.robot.auto

import edu.wpi.first.wpilibj.command.CommandGroup

abstract class AutoMode : CommandGroup() {

    abstract fun addCommands()

    init {
        addCommands()
    }
}
