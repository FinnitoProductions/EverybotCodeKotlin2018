package frc.robot

import frc.robot.commands.*
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Intake
import harkerrobolib.subsystems.HSArm
import harkerrobolib.subsystems.HSIntake
import harkerrobolib.wrappers.LogitechAnalogGamepad
import harkerrobolib.wrappers.LogitechGamepad
import harkerrobolib.wrappers.XboxGamepad

/**
 * Handles all of the teleoperated input given to the robot, most notably through the joysticks.
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 9/27/18
 */
object OI {
    val DRIVER_PORT = 0
    val OPERATOR_PORT = 1
    val driverGamepad = XboxGamepad(DRIVER_PORT)
    val operatorGamepad = LogitechAnalogGamepad(OPERATOR_PORT)
    val JOYSTICK_UP = 1

    val XBOX_DEADBAND = 0.1
    val LOGITECH_DEADBAND = 0.1

    init {
        //driver arm buttons
        driverGamepad.buttonA.whenPressed(MoveArmPosition(Arm.MAX_POSITION_DOWN_SPEED, HSArm.ArmDirection.DOWN))
        driverGamepad.buttonY.whenPressed(MoveArmPosition(Arm.MAX_POSITION_UP_SPEED, HSArm.ArmDirection.UP))

        //operator arm buttons
        /*operatorGamepad.buttonA.whenPressed(MoveArmPosition(Arm.MAX_POSITION_DOWN_SPEED, HSArm.ArmDirection.DOWN))
        operatorGamepad.buttonY.whenPressed(MoveArmPosition(Arm.MAX_POSITION_UP_SPEED, HSArm.ArmDirection.UP))*/

        //operator intake and outtake buttons
        val intakeCommand = IntakeOuttakeIndefinite(Intake.DEFAULT_INTAKE_SPEED, HSIntake.IntakeDirection.IN)
        operatorGamepad.buttonA.whenPressed(intakeCommand)
        operatorGamepad.buttonA.cancelWhenReleased(intakeCommand)

        val outtakeCommand = IntakeOuttakeIndefinite(-Intake.DEFAULT_OUTTAKE_SPEED, HSIntake.IntakeDirection.OUT)
        operatorGamepad.buttonY.whenPressed(outtakeCommand)
        operatorGamepad.buttonY.cancelWhenReleased(outtakeCommand)

        //toggle arcade and tank
        //driverGamepad.buttonStart.whenPressed(ToggleArcadeTank())
    }
}