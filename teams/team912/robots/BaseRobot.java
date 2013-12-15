package team912.robots;

import team912.BotComponent;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;


public abstract class BaseRobot extends BotComponent {
	public BaseRobot(RobotController rc) {
		super(rc);
	}

	public abstract void run() throws GameActionException;
	
	protected boolean isSafeLocation(MapLocation location) throws GameActionException{
		boolean safe = true;
		Team team = this.getControl().senseMine(location);
		// if there is a mine
		if(team != null){
			safe = (team == this.getControl().getTeam());
		} else {
			// no mine, check if square is occupied
			safe = (this.getControl().senseObjectAtLocation(location) == null);
		}
		return safe;
	}
}
