package team912.mapping;

import battlecode.common.Direction;
import battlecode.common.MapLocation;

public interface Mapper {
	MapLocation getEnemyHQLocation();
	MapLocation getClosestMineableLocation(MapLocation location);
	MapLocation getOwnHQLocation();
	MapLocation getClosestTo(MapLocation startLocation, Iterable<MapLocation> potentialLocations);
	Direction getDirToEnemyHQ();
	Direction getDirToOwnHQ();
}
