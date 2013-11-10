package team912.robots;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import team912.BotComponent;


public abstract class BaseRobot extends BotComponent {
	public BaseRobot(RobotController rc) {
		super(rc);
	}

	public abstract void run() throws GameActionException;
}
