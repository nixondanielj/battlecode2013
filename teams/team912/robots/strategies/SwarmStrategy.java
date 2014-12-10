package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.Team;

class SwarmStrategy implements IStrategy {

	@Override
	public MapLocation getTarget(Mapper mapper, RobotController rc) throws GameActionException {
		GameObject[] nearbyEnemeies = rc.senseNearbyGameObjects(Robot.class, 10, Team.A);
		if (nearbyEnemeies.length > 0)
			return rc.senseLocationOf(nearbyEnemeies[0]);
		if (rc.getTeamPower() > 50)
			return mapper.getOwnHQLocation().add(mapper.getDirToEnemyHQ(), 5);
		return mapper.getEnemyHQLocation();
	}

	@Override
	public int[] getMessage(RobotController rc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doPreMoveAction(RobotController rc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPostMoveAction(RobotController rc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doNoMoveAction(RobotController rc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInactiveAction(RobotController rc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAtTargetAction(RobotController c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldAvoid(Team mineTeam, Team ownTeam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldDefuse(Team mineTeam, Team ownTeam) {
		return mineTeam != ownTeam;
	}

}
