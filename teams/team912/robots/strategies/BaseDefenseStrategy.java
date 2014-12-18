package team912.robots.strategies;

import team912.mapping.Mapper;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

class BaseDefenseStrategy implements IStrategy {

	@Override
	public MapLocation getTarget(Mapper mapper) throws GameActionException {
		return mapper.getClosestMineableLocation(mapper.getOwnHQLocation());
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
	public void doAtTargetAction(RobotController c) throws GameActionException {
		c.layMine();
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
