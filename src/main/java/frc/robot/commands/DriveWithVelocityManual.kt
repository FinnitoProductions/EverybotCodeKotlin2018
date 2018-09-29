package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.DrivetrainConstants
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

class DriveWithVelocityManual : Command(){
    var speed = 0.0
    var turn = 0.0
    override fun end() {

    }

    override fun execute() {
        speed = mapSpeed(OI.gamepad.leftY)
        turn = mapTurn(OI.gamepad.leftX)
        Drivetrain.arcadeDrivePercentOutput(speed,turn)

    }

    private fun mapSpeed(givenSpeed : Double): Double {
        //checks if gamepad speed output is between [-1, 1] and if so, sets speed to 0
        if( givenSpeed >= -Global.DEADBAND &&
                givenSpeed <= Global.DEADBAND) {
            return 0.0
        }
    }

    private fun mapTurn(givenTurn : Double) : Double {
        if(givenTurn >= -Global.DEADBAND &&
           givenTurn <= Global.DEADBAND) {
            return 0.0
        }
    }

    override fun initialize() {

    }
    override fun isFinished() : Boolean = false

}