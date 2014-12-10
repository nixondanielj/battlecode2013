package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

public interface IStrategy {
	MapLocation getTarget(Mapper mapper, RobotController rc) throws GameActionException;
	
	int[] getMessage(RobotController rc);
	
	void doPreMoveAction(RobotController rc);
	
	void doPostMoveAction(RobotController rc);
	
	void doNoMoveAction(RobotController rc);
	
	void doInactiveAction(RobotController rc);

	void doAtTargetAction(RobotController c) throws GameActionException;

	boolean shouldAvoid(Team mineTeam, Team ownTeam);

	boolean shouldDefuse(Team mineTeam, Team ownTeam);
}
