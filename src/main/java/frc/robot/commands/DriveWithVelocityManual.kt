package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.DrivetrainConstants
import frc.robot.Global
import frc.robot.OI
import frc.robot.subsystems.Drivetrain
import frc.robot.util.MathUtil
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper

class DriveWithVelocityManual : Command() {
    var speed = 0.0
    var turn = 0.0

    init {
        requires(Drivetrain)
    }

    /**
     * Sets the gamepad speed and turn output
     * to the desired values to move the robot optimally.
     */
    override fun execute() {
        speed = MathUtil.mapOutput(OI.gamepad.leftY, Global.DEADBAND)
        turn = MathUtil.mapOutput(OI.gamepad.leftX, Global.DEADBAND)
        Drivetrain.arcadeDrivePercentOutput(speed * OI.JOYSTICK_UP, turn)
    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}