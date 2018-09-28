package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

class DriveWithVelocityManual : Command(){
    override fun end() {

    }
    override fun execute() {
        Drivetrain.arcadeDrivePercentOutput(OI.gamepad.leftY,OI.gamepad.leftX)
    }
    override fun initialize() {

    }
    override fun isFinished() : Boolean = false

}