package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

class SaboteurStrategy implements IStrategy {
	
	private int mineAvoidanceChecks = 0;

	@Override
	public MapLocation getTarget(Mapper mapper) throws GameActionException {
		MapLocation closestEnemyBuilding =  mapper.getClosestEnemyBuilding();
		if(closestEnemyBuilding == null){
			return mapper.getEnemyHQLocation();
		}
		return closestEnemyBuilding;
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
		mineAvoidanceChecks = 0;
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
		mineAvoidanceChecks++;
		return mineTeam != ownTeam;
	}

	@Override
	public boolean shouldDefuse(Team mineTeam, Team ownTeam) {
		return mineAvoidanceChecks < 3 && mineTeam != ownTeam;
	}

}
