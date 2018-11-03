package frc.robot.commands

import edu.wpi.first.wpilibj.command.TimedCommand
import frc.robot.subsystems.Drivetrain

class ArcadeDriveVelocityTimed(val time: Double, val speed: Double) : TimedCommand(time) {

    override fun execute() {
        Drivetrain.arcadeDrivePercentOutput(speed, 0.0)
    }

}