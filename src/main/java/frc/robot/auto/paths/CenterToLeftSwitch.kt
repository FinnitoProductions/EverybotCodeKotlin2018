package frc.robot.auto.paths

import frc.robot.Autonomous
import harkerrobolib.auto.Path
import jaci.pathfinder.Waypoint

/**
 * Represents a path starting from the center of the field to the left side switch (head-on).
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/27/18
 */
class CenterToLeftSwitch : Path(Autonomous.CENTER_TO_LEFT_SWITCH_WAYPOINTS.toTypedArray(), accelMax = 10.5)