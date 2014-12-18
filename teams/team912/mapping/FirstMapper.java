package team912.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import team912.BotComponent;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

public class FirstMapper extends BotComponent implements Mapper {

	public FirstMapper(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MapLocation getEnemyHQLocation() {
		return this.getControl().senseEnemyHQLocation();
	}

	@Override
	public MapLocation getClosestMineableLocation(MapLocation location)
			throws GameActionException {
		// if the given location is mineable and unoccupied, skip everything and
		// return it
		if (!this.isMineable(location) || this.isOccupied(location)) {
			List<MapLocation> potentialLocations = new ArrayList<MapLocation>();
			for (int x = location.x - 3; x <= location.x + 3; x++) {
				for (int y = location.y - 3; y <= location.y + 3; y++) {
					MapLocation potentialLoc = new MapLocation(x, y);
					if (this.getControl().canSenseSquare(potentialLoc)) {
						// if location is mineable and unoccupied
						if (this.isMineable(potentialLoc)
								&& !this.isOccupied(potentialLoc)) {
							potentialLocations.add(potentialLoc);
						}
					}
				}
			}
			location = getClosestTo(location, potentialLocations);
		}
		return location;
	}
	
	public MapLocation getClosestTo(MapLocation start, MapLocation... potentialLocations){
		if(potentialLocations.length == 1){
			return potentialLocations[0];
		}
		return getClosestTo(start, Arrays.asList(potentialLocations));
	}

	public MapLocation getClosestTo(MapLocation startLocation,
			Iterable<MapLocation> potentialLocations) {
		if(potentialLocations == null){
			return null;
		}
		MapLocation closest = null;
		int shortestDist = 0;
		for (MapLocation location : potentialLocations) {
			int distance = startLocation.distanceSquaredTo(location);
			if (closest == null || distance < shortestDist) {
				closest = location;
				shortestDist = distance;
			} else if (distance == shortestDist) {
				MapLocation myLoc = this.getControl().getLocation();
				int distToLoc = myLoc.distanceSquaredTo(location);
				int distToClosest = myLoc.distanceSquaredTo(closest);
				if (distToLoc < distToClosest) {
					closest = location;
				} else if (distToLoc == distToClosest) {
					distToLoc = location.distanceSquaredTo(this
							.getEnemyHQLocation());
					distToClosest = closest.distanceSquaredTo(this
							.getEnemyHQLocation());
					if (distToLoc < distToClosest) {
						closest = location;
					}
				}
			}
		}
		return closest;
	}

	@Override
	public MapLocation getOwnHQLocation() {
		return this.getControl().senseHQLocation();
	}

	@Override
	public Direction getDirToEnemyHQ() {
		return this.getControl().getLocation()
				.directionTo(this.getEnemyHQLocation());
	}

	@Override
	public Direction getDirToOwnHQ() {
		return this.getControl().getLocation()
				.directionTo(this.getOwnHQLocation());
	}

	@Override
	public MapLocation getClosestNonAlliedBuilding() throws GameActionException {
		MapLocation[] nonAlliedEncampments = null;
		for (int range = 20; (nonAlliedEncampments == null || nonAlliedEncampments.length == 0) && range < 100; range += 5) {
			nonAlliedEncampments = getControl().senseEncampmentSquares(
				getControl().getLocation(), range, Team.NEUTRAL);
		}
		if (nonAlliedEncampments.length == 1) {
			return nonAlliedEncampments[0];
		}
		return getClosestTo(getControl().getLocation(), nonAlliedEncampments);
	}

	@Override
	/***
	 * Gets the closest NON HQ enemy building location or null if no enemy encampments are within sensor range
	 */
	public MapLocation getClosestEnemyBuilding() throws GameActionException {
		List<MapLocation> enemyEncampments = new ArrayList<MapLocation>();
		for (int range = 20; (enemyEncampments == null || enemyEncampments.size() == 0) && range < 100; range += 5) {
			MapLocation[] potentialEncampments = getControl().senseEncampmentSquares(
				getControl().getLocation(), range, Team.NEUTRAL);
			for(int i = potentialEncampments.length - 1; i >=0; i--){
				MapLocation loc = potentialEncampments[i];
				// if we can sense the square, and it is not the enemy HQ
				if(getControl().canSenseSquare(loc) && !loc.equals(getEnemyHQLocation())){
					GameObject objectAtLoc = getControl().senseObjectAtLocation(loc);
					if(objectAtLoc != null && objectAtLoc.getTeam() != getControl().getTeam()){
						enemyEncampments.add(loc);
					}
				}
			}
		}
		return getClosestTo(getControl().getLocation(), enemyEncampments);
	}

}
