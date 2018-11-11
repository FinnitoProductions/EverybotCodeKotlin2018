package frc.robot.commands

import edu.wpi.first.wpilibj.command.CommandGroup
import harkerrobolib.auto.SequentialCommandGroup
import harkerrobolib.subsystems.HSArm

/**
 * Contains all autonomous commands as one command group.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/11/18
 */
class AutonomousCommand : CommandGroup() {

    init {
        addSequential(
                SequentialCommandGroup(ArcadeDriveVelocityTimed(3.0, -0.5), MoveArmPosition(1, HSArm.ArmDirection.UP))

        )
    }


}