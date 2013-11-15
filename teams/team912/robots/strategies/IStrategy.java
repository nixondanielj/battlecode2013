package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public interface IStrategy {
	MapLocation getTarget(Mapper mapper);
	
	int[] getMessage(RobotController rc);
	
	void doPreMoveAction(RobotController rc);
	
	void doPostMoveAction(RobotController rc);
	
	void doNoMoveAction(RobotController rc);
	
	void doInactiveAction(RobotController rc);
}
