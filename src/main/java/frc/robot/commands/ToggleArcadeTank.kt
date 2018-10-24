package frc.robot.commands

import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand

class ToggleArcadeTank : InstantCommand() {
    private val ad = ArcadeDriveVelocity()
    private val td = TankDriveVelocity()
    private val ap = Arm.DEFAULT_ARM_COMMAND
    private val id = IndefiniteCommand()

    init {
        requires(Drivetrain)
    }
    
    override fun initialize() {
        if (Drivetrain.defaultCommand is TankDriveVelocity) {
            Drivetrain.defaultCommand = ad
            Arm.defaultCommand = ap
        } else if (Drivetrain.defaultCommand is ArcadeDriveVelocity) {
            Drivetrain.defaultCommand = td
            Arm.defaultCommand = id
        }
    }
}