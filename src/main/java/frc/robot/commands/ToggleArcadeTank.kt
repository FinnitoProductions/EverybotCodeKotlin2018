package frc.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand

class ToggleArcadeTank : InstantCommand() {
    private val ad = ArcadeDriveVelocity()
    private val td = TankDriveVelocity()
    private val armDefault = Arm.defaultCommand
    private val id = IndefiniteCommand()

    init {
        requires(Drivetrain)
    }

    override fun initialize() {
        if (Drivetrain.defaultCommand is TankDriveVelocity) {
            Drivetrain.defaultCommand = ad
            Arm.defaultCommand = armDefault
        } else if (Drivetrain.defaultCommand is ArcadeDriveVelocity) {
            Drivetrain.defaultCommand = td
            Arm.defaultCommand = id
        }
    }
}