package frc.robot

import edu.wpi.first.wpilibj.*
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.auto.AutoMode
import frc.robot.commands.AutonomousCommand
import frc.robot.commands.DriveToPosition
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import frc.robot.subsystems.Intake

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
object Robot : TimedRobot() {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    override fun robotInit() {
        Drivetrain
        Intake
        Arm
        OI
    }

    /**
     * This function is run once each time the robot enters autonomous mode.
     */
    override fun autonomousInit() {
        Drivetrain.talonInit()
        Intake.talonInit()
        Arm.talonInit()
        AutonomousCommand()
        AutonomousCommand(Baseline().start())
    }

    /**
     * This function is called periodically during autonomous.
     */
    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
    }

    /**
     * This function is called once each time the robot enters teleoperated mode.
     */
    override fun teleopInit() {
        Drivetrain.talonInit()
        Intake.talonInit()
        Arm.talonInit()
    }

    /**
     * This function is called periodically during teleoperated mode.
     */
    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
        SmartDashboard.putNumber("Drivetrain leftMaster's encoder position:", Drivetrain.leftMaster.getSelectedSensorPosition(Drivetrain.PID_PRIMARY).toDouble())
        SmartDashboard.putNumber("Drivetrain rightMaster's encoder position:", Drivetrain.rightMaster.getSelectedSensorPosition(Drivetrain.PID_PRIMARY).toDouble())
    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {}
}
