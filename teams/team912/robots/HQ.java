package team912.robots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
//import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class HQ extends BaseRobot {
	
	public HQ(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws GameActionException {
		if (this.getControl().isActive()) {
			spawn();
		}
	}

	private void spawn() throws GameActionException {
		Direction direction;
		direction = getBestSpawnDirection();
		this.getControl().spawn(direction);
	}

	private Direction getBestSpawnDirection() throws GameActionException {
		RobotController c = this.getControl();
		MapLocation hqLoc = c.getLocation();
		MapLocation enLoc = c.senseEnemyHQLocation();
		Direction safeDir = hqLoc.directionTo(enLoc);
		
		while(!isSafeLocation(hqLoc.add(safeDir))){
			safeDir = safeDir.rotateLeft();
		}
		return safeDir;
	}
}