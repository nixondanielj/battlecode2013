package team912.pathing;

import team912.BotComponent;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public abstract class Pather extends BotComponent {
	Pather(RobotController robot){
		super(robot);
	}
	
	public abstract Direction dirTo(MapLocation location);

	public void avoid(MapLocation add) {
		// TODO Auto-generated method stub
		
	}

	public void clearAvoids() {
		// TODO Auto-generated method stub
		
	}
}
