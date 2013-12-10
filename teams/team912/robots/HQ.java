package team912.robots;

//import team912.mapping.Mapper;
//import team912.pathing.BadPather;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
//import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.Team;

public class HQ extends BaseRobot {
	public HQ(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws GameActionException {
		if (this.getControl().isActive()) {
			Direction direction;
			direction = getBestSpawnDirection();
			this.getControl().spawn(direction);
		}
	}
	
	private Direction getBestSpawnDirection() throws GameActionException{
		RobotController c = this.getControl();
		MapLocation hqLoc = c.getLocation();
		MapLocation enLoc = c.senseEnemyHQLocation();
		Direction enemyHqDir = hqLoc.directionTo(enLoc);
		
		if(isSafeLocation(hqLoc.add(enemyHqDir), c)){
			return enemyHqDir;
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateLeft()), c)){
			return enemyHqDir.rotateLeft();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateRight()), c)){
			return enemyHqDir.rotateRight();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateLeft().rotateLeft()), c)){
			return enemyHqDir.rotateLeft().rotateLeft();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateRight().rotateRight()), c)){
			return enemyHqDir.rotateRight().rotateRight();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateLeft().rotateLeft().rotateLeft()), c)){
			return enemyHqDir.rotateLeft().rotateLeft().rotateLeft();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.rotateRight().rotateRight().rotateRight()), c)){
			return enemyHqDir.rotateRight().rotateRight().rotateRight();
		} else if (isSafeLocation(hqLoc.add(enemyHqDir.opposite()), c)){
			return enemyHqDir.opposite();
		}
		return enemyHqDir;
	}
	
	private boolean isSafeLocation(MapLocation location, RobotController c) throws GameActionException{		
		Team mine = c.senseMine(location);
				
		if (mine == c.getTeam() || mine == null){
			return true;
		}
		return false;
	}
}