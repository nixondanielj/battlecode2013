package team912.robots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class HQ extends BaseRobot {
	public HQ(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	boolean keepSpawning = true;

	@Override
	public void run() throws GameActionException {
		if (this.getControl().isActive()) {
			Direction direction;
			GameObject obstacle;
			do {
				direction = Direction.values()[(int) (Math.random() * Direction
						.values().length)];
				MapLocation location = this.getControl().getLocation()
						.add(direction);
				obstacle = this.getControl().senseObjectAtLocation(location);
			} while (obstacle != null);
			if (keepSpawning) {
				this.getControl().spawn(direction);
				//keepSpawning = false;
			}
		}
	}

}
