package team912.pathing;

import battlecode.common.Direction;
import battlecode.common.MapLocation;

public interface IPather {
	Direction dirTo(MapLocation location);
}
