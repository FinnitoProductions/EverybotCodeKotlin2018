package frc.robot.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.command.Command
import frc.robot.Global
import frc.robot.subsystems.Drivetrain
import harkerrobolib.util.MathUtil

class TurnToAngle(val angle : Double) : Command() {

    val setpoints : Array<Double>
    init {
       setpoints = MathUtil.turnInPlace(angle, Global.ROBOT_WIDTH)

    }
    override fun initialize(){
        Drivetrain.leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Global.PID_PRIMARY, Global.TIMEOUT)

        Drivetrain.leftMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Global.PID_PRIMARY)
        Drivetrain.rightMaster.selectProfileSlot(Drivetrain.POSITION_PID_SLOT, Global.PID_PRIMARY)

        Drivetrain.leftMaster.setSensorPhase(Drivetrain.LEFT_ENCODER_PHASE)
        Drivetrain.rightMaster.setSensorPhase(Drivetrain.RIGHT_ENCODER_PHASE)

        Drivetrain.leftMaster.setSelectedSensorPosition(0, Global.PID_PRIMARY, Global.TIMEOUT)
        Drivetrain.rightMaster.setSelectedSensorPosition(0, Global.PID_PRIMARY, Global.TIMEOUT)
    }

    override fun execute() {
        Drivetrain.leftMaster[ControlMode.Position] = setpoints[0]
        Drivetrain.rightMaster[ControlMode.Position] = setpoints[1]
    }
    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}