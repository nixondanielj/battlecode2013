package team912.pathing;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class BadPather extends Pather {

	public BadPather(RobotController robot) {
		super(robot);
	}

	@Override
	public Direction dirTo(MapLocation location) {
		Direction direction = this.getControl().getLocation().directionTo(location);
		while(!this.canMove(direction)){
			direction = direction.rotateLeft();
		}
		return direction;
	}

}
