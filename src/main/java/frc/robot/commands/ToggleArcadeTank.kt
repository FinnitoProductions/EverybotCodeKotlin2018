package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.InstantCommand
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import harkerrobolib.commands.IndefiniteCommand

class ToggleArcadeTank : InstantCommand() {
    private val ad = ArcadeDriveVelocity()
    private val td = TankDriveVelocity()
    private val armDefault = Arm.defaultCommand
    private val id = object : Command() {
        override fun isFinished() : Boolean {
            return false
        }

        init {
            requires (Arm)
        }
    }

    override fun initialize() {
        println (Drivetrain.defaultCommand.javaClass)
        println (Arm.defaultCommand.javaClass)
        if (Drivetrain.defaultCommand is TankDriveVelocity) {
            println ("Changing to Arcade")
            Drivetrain.defaultCommand = ad
            Arm.defaultCommand = armDefault
        } else  {
            println ("Changing to Tank")
            Drivetrain.defaultCommand = td
            Arm.defaultCommand = id
        }
    }
}