package frc.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand

class ToggleArcadeTank : InstantCommand() {

    init {
        requires(Drivetrain)
    }
    override fun initialize() {
        val ad = ArcadeDriveVelocity()
        val td = TankDriveVelocity()
        val ap = Arm.DEFAULT_ARM_COMMAND
        val id = IndefiniteCommand()
        if (Drivetrain.defaultCommand is TankDriveVelocity) {
            Drivetrain.defaultCommand = ad
            Arm.defaultCommand = ap
        } else if (Drivetrain.defaultCommand is ArcadeDriveVelocity) {
            Drivetrain.defaultCommand = td
            Arm.defaultCommand = id
        }
    }
}