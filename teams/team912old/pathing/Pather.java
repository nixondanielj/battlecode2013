package team912old.pathing;

import team912old.BotComponent;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public abstract class Pather extends BotComponent {
	Pather(RobotController robot){
		super(robot);
		this.setCurrentLocation(robot.getLocation());
	}
	
	public abstract Direction dirTo(MapLocation location);

	private MapLocation currentLocation;
	protected MapLocation getCurrentLocation(){
		return this.currentLocation;
	}
	public void setCurrentLocation(MapLocation location){
		this.currentLocation = location;
	}
}
