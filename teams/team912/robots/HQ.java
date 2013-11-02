package team912.robots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;


public class HQ extends BaseRobot {
	public HQ(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws GameActionException {
		this.getControl().spawn(Direction.values()[(int) (Math.random() * Direction.values().length)]);
	}

}
