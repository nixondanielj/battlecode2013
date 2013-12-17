package team912.mapping;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

public interface Mapper {
	MapLocation getEnemyHQLocation();
	MapLocation getClosestMineableLocation(MapLocation location) throws GameActionException;
	MapLocation getOwnHQLocation();
	MapLocation getClosestTo(MapLocation startLocation, Iterable<MapLocation> potentialLocations);
	Direction getDirToEnemyHQ();
	Direction getDirToOwnHQ();
}
