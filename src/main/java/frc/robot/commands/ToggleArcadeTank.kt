package frc.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Drivetrain

class ToggleArcadeTank() : InstantCommand() {

    init {
        requires(Drivetrain)
    }
    override fun initialize() {
        if (Drivetrain.defaultCommand is TankDrive) {
            Drivetrain.defaultCommand = DriveWithVelocityManual()
        } else if (Drivetrain.defaultCommand is DriveWithVelocityManual) {
            Drivetrain.defaultCommand = TankDrive()
        }
    }

}