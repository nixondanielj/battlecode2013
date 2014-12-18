package team912.mapping;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

public interface Mapper {
	MapLocation getEnemyHQLocation();
	MapLocation getClosestMineableLocation(MapLocation location) throws GameActionException;
	MapLocation getOwnHQLocation();
	MapLocation getClosestTo(MapLocation start, Iterable<MapLocation> potentialLocations);
	MapLocation getClosestTo(MapLocation start, MapLocation... potentialLocations);
	Direction getDirToEnemyHQ();
	Direction getDirToOwnHQ();
	MapLocation getClosestNonAlliedBuilding() throws GameActionException;
	MapLocation getClosestEnemyBuilding() throws GameActionException;
}
