package team912.mapping;

import java.util.ArrayList;
import java.util.List;

import team912.BotComponent;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

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
	public MapLocation getClosestMineableLocation(MapLocation location) {
		// if the location is mineable, skip everything and return it
		if(!this.isLocationMineable(location)){
			List<MapLocation> potentialLocations = new ArrayList<MapLocation>();
			MapLocation[] minedLocations = this.getControl().senseMineLocations(location, 25, null);
			for(MapLocation minedLocation : minedLocations){
				// if the mine is not ours, add to potential locations
				if(this.getControl().senseMine(minedLocation) != this.getControl().getTeam()){
					potentialLocations.add(minedLocation);
				}
			}
			location = getClosestTo(location, potentialLocations);
		}
		return location;
	}

	public MapLocation getClosestTo(MapLocation startLocation,
			Iterable<MapLocation> potentialLocations) {
		MapLocation closest = null;
		int shortestDist = 0;
		for(MapLocation location : potentialLocations){
			int distance = startLocation.distanceSquaredTo(location);
			if(closest == null || distance < shortestDist){
				closest = location;
				shortestDist = distance;
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

}
