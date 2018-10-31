package frc.robot.auto.paths

import frc.robot.Autonomous
import harkerrobolib.auto.Path
import harkerrobolib.util.MathUtil
import jaci.pathfinder.Waypoint

/**
 * Represents a path starting from the center of the field to the left side switch (head-on).
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/30/18
 */
class CenterToRightSwitch : Path(MathUtil.reflectY(Autonomous.CENTER_TO_LEFT_SWITCH_WAYPOINTS, Autonomous.CENTER_Y), accelMax = 10.5)