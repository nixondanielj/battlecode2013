package team912old.pathing;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class BadPather extends Pather {

	public BadPather(RobotController robot) {
		super(robot);
	}

	@Override
	public Direction dirTo(MapLocation location) {
		Direction direction = this.getCurrentLocation().directionTo(location);
		while(!this.getControl().canMove(direction)){
			direction = direction.rotateLeft();
		}
		return direction;
	}

}
