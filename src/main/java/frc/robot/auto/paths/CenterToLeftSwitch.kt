package frc.robot.auto.paths

import harkerrobolib.auto.Path
import jaci.pathfinder.Waypoint

/**
 * Represents a path starting from the center of the field to the left side switch (head-on).
 *
 * @author  Angela Jia
 * @author  Finn Frankis
 * @version 10/27/18
 */
class CenterToLeftSwitch : Path(arrayOf(Waypoint(1.64, 13.00, 0.0), Waypoint(10.65,17.85, 20.00)),accelMax = 10.5)