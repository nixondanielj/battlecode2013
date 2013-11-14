package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public interface IStrategy {
	MapLocation getTarget(Mapper mapper);
	
	int[][] getMessage(RobotController rc);
}
