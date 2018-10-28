package frc.robot

import edu.wpi.first.wpilibj.*
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.AutonomousCommand
import frc.robot.auto.mode.Baseline
import frc.robot.subsystems.Arm
import frc.robot.subsystems.Drivetrain
import frc.robot.subsystems.Intake
import harkerrobolib.auto.AutoMode

/**
 * Represents the overall root of the project, ensuring all subsystems are instantiated and
 * periodically ensuring all commands are called as expected.
 *
 * @author Angela Jia
 * @version 9/27/18
 */
class Robot : TimedRobot() {

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
        AutonomousCommand(Baseline(AutoMode.StartLocation.LEFT)).start();
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

        
            SmartDashboard.putNumber ("Arm Current", Arm.getTalonCurrent())
        }

        /**
         * This function is called periodically during test mode.
         */
        override fun testPeriodic() {}
    }

