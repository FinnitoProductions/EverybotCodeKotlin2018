package frc.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Drivetrain

class ToggleArcadeTank() : InstantCommand() {

    init {
        requires(Drivetrain)
    }
    override fun initialize() {
        if (Drivetrain.defaultCommand is TankDriveVelocity) {
            Drivetrain.defaultCommand = ArcadeDriveVelocity()
        } else if (Drivetrain.defaultCommand is ArcadeDriveVelocity) {
            Drivetrain.defaultCommand = TankDriveVelocity()
        }
    }
}